package org.youcode.citronix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.exception.Farm.FarmNotFoundException;
import org.youcode.citronix.exception.Farm.InvalidFarmException;
import org.youcode.citronix.exception.InvalidCredentialsException;
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
        when(farmRepository.save(farm)).thenReturn(savedFarm);

        //When
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
    void FarmService_getAllFarms_returnsListOfAllFarms() {

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
        when(farmRepository.findAll()).thenReturn(expectedList);

        //When
        List<Farm> resultedList = farmService.getAllFarms();

        //Then
        verify(farmRepository).findAll();
        assertEquals(2,resultedList.size());
        assertEquals(expectedList,resultedList);
    }

    @Test
    void FarmService_getFarmsWithPagination_returnsPagesOfAllFarm() {
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

        List<Farm> farms = List.of(farm1,farm2);
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farmPage = new PageImpl<>(farms);

        when(farmRepository.findAll(pageable)).thenReturn(farmPage);

        // When
        Page<Farm> result = farmService.getFarmsWithPagination(page, size);

        //Then
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(farmRepository, times(1)).findAll(pageable);
    }

    @Test
    public void FarmService_getFarmsWithPagination_NoFarmsAvailable() {
        // Given
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        when(farmRepository.findAll(pageable)).thenReturn(Page.empty());

        // When
        Page<Farm> result = farmService.getFarmsWithPagination(page, size);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(farmRepository).findAll(pageable);
    }

    @Test
    public void FarmService_getFarmById_returnFarm(){

        // Given
        UUID farmId = UUID.randomUUID();
        Farm farm = Farm.builder()
                .id(farmId)
                .build();

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        // When
        Farm resultedFarm = farmService.getFarmById(farmId);

        // Then
        assertNotNull(resultedFarm);
        assertEquals(farmId, resultedFarm.getId());
        verify(farmRepository, times(1)).findById(farmId);
    }

    @Test
    public void FarmService_getFarmById_throwsInvalidCredentialsException_whenIdIsNull(){

        // Given
        UUID farmId = null;

        // When &Then
        assertThrows(InvalidCredentialsException.class,
                ()->farmService.getFarmById(farmId));
        verify(farmRepository,never()).findById(farmId);

    }

    @Test
    public void FarmService_getFarmById_throwsFarmNotFoundException(){

        //Given
        UUID id = UUID.randomUUID();
        when(farmRepository.findById(id)).thenReturn(Optional.empty());

        //When & Then
        assertThrows(FarmNotFoundException.class,
                ()->farmService.getFarmById(id));
        verify(farmRepository).findById(id);
    }

    }