package org.youcode.citronix.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.domain.entities.Field;
import org.youcode.citronix.exception.Farm.FarmSizeException;
import org.youcode.citronix.exception.InvalidCredentialsException;
import org.youcode.citronix.repository.FieldRepository;
import org.youcode.citronix.service.Implementations.FieldServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FieldServiceImplTest {

    @Mock
    FieldRepository fieldRepository;

    @Mock
    FarmService farmService;

    @InjectMocks
    FieldServiceImpl fieldService;

    private Farm farm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
         farm = Farm.builder()
                .id(UUID.randomUUID())
                .name("farm")
                .location("location")
                .area(100.0)
                .fields(new ArrayList<>())
                .build();
    }

    @Test
    void FieldService_addField_succeed(){
        // Given
        Field field =  Field.builder()
                .area(20.0)
                .farm(farm)
                .build();

        Field savedField =  Field.builder()
                .id(UUID.randomUUID())
                .area(20.0)
                .farm(farm)
                .build();
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);
        when(fieldRepository.save(field)).thenReturn(savedField);

        // When
        Field result = fieldService.addField(field);

        // Then
        assertNotNull(result);
        assertEquals(savedField.getId(), result.getId());
        verify(fieldRepository).save(field);
    }

    @Test
    void FieldService_addField_throwsInvalidCredentialsException_whenFieldAreaTooSmall() {

        // Given
        Field field =  Field.builder()
                .area(0.05)
                .farm(farm)
                .build();
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);

        // When & Then
       assertThrows(InvalidCredentialsException.class, () -> {
            fieldService.addField(field);
        });
        verify(fieldRepository,never()).save(field);
    }

    @Test
    void FieldService_addField_throwsInvalidCredentialsException_whenFieldAreaExceedsFarmArea() {
        // Given
        Field field =  Field.builder()
                .area(70)
                .farm(farm)
                .build();
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);

        // When & Then
        assertThrows(InvalidCredentialsException.class, () -> {
            fieldService.addField(field);
        });
        verify(fieldRepository,never()).save(field);
    }

    @Test
    void FieldService_addField_throwsFarmSizeException_whenFarmHasMoreThan10Fields() {
        // Given
        Field field =  Field.builder()
                .area(20)
                .farm(farm)
                .build();

        for (int i = 0; i < 10; i++) {
            farm.getFields().add(new Field());
        }
        when(farmService.getFarmById(farm.getId())).thenReturn(farm);

        // When & Then
        assertThrows(FarmSizeException.class, () -> {
            fieldService.addField(field);
        });
        verify(fieldRepository,never()).save(field);
    }

    }