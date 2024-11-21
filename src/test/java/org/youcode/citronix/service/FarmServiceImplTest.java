package org.youcode.citronix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.exception.Farm.InvalidFarmException;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.repository.FarmSearchRepository;
import org.youcode.citronix.service.Implementations.FarmServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void FarmService_save_throwsInvalidFarmException() {

        //When & Then
        assertThrows(InvalidFarmException.class,
                ()-> farmService.save(null)
        );
        verify(farmRepository,never()).save(any(Farm.class));
    }

    @Test
    void FarmService_getAllFarms_returnsAllFarm() {

        //Given
        Farm farm1 = Farm.builder()
                .name("name 1")
                .area(200)
                .creationDate(LocalDate.parse("2024-01-01"))
                .location("location 1")
                .build();

        Farm farm2 = Farm.builder()
                .name("name 2")
                .area(200)
                .creationDate(LocalDate.parse("2024-03-03"))
                .location("location 2")
                .build();

        List<Farm> expectedList = List.of(farm1,farm2);

        //When

        when(farmRepository.findAll()).thenReturn(expectedList);
        List<Farm> resultedList = farmService.getAllFarms();

        //Then
        verify(farmRepository).findAll();
        assertEquals(2,resultedList.size());
        assertEquals(expectedList,resultedList);
    }



    }