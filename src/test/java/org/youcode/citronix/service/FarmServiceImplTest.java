package org.youcode.citronix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.repository.FarmSearchRepository;
import org.youcode.citronix.service.Implementations.FarmServiceImpl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FarmServiceImplTest {

    @Mock
    FarmRepository farmRepository;

    @Mock
    FarmSearchRepository farmSearchRepository;

    @InjectMocks
    FarmServiceImpl farmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void FarmService_save_succeed() {

        //Given
        String name ="farm test";
        double area = 150;
        LocalDate creationDate = LocalDate.parse("2024-01-01");
        String location = "location tes";

        Farm farm = Farm.builder()
                .name(name)
                .area(area)
                .creationDate(creationDate)
                .location(location)
                .build();

        Farm savedFarm = Farm.builder()
                .id(UUID.randomUUID())
                .name(name)
                .area(area)
                .creationDate(creationDate)
                .location(location)
                .build();

        //When
        when(farmRepository.save(farm)).thenReturn(savedFarm);

        Farm resultedFarm = farmService.save(farm);

        //Then
        assertNotNull(resultedFarm);
        assertEquals(resultedFarm.getId(),savedFarm.getId());
        verify(farmRepository).save(farm);
    }



}