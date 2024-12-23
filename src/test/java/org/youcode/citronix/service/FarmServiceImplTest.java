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
import org.youcode.citronix.DTO.SearchFarmDTO;
import org.youcode.citronix.domain.entities.Farm;
import org.youcode.citronix.exception.Farm.FarmNotFoundException;
import org.youcode.citronix.exception.Farm.InvalidFarmException;
import org.youcode.citronix.exception.InvalidCredentialsException;
import org.youcode.citronix.repository.FarmRepository;
import org.youcode.citronix.repository.FarmSearchRepository;
import org.youcode.citronix.service.Implementations.FarmServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
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
    @Test
    void FarmService_updateFarm_succeed(){

        //Given
        UUID farmId = UUID.randomUUID();
        Farm existingFarm = Farm.builder()
                .id(farmId)
                .name("old name")
                .area(200)
                .creationDate(LocalDate.now())
                .location("old location")
                .build();

        Farm farm = Farm.builder()
                .id(null)
                .name("new name")
                .area(200)
                .creationDate(LocalDate.now())
                .location("new location")
                .build();

        Farm updatedFarm = Farm.builder()
                .id(farmId)
                .name("new name")
                .area(250)
                .creationDate(LocalDate.now())
                .location("new location")
                .build();

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(existingFarm));
        when(farmRepository.save(any(Farm.class))).thenReturn(updatedFarm);

        //When
        Farm result = farmService.updateFarm(farmId, farm);

        //Then
        assertNotNull(result);
        verify(farmRepository).findById(farmId);
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    void FarmService_updateFarm_throwsInvalidCredentialsException_whenIdIsNull(){
        UUID farmId = null;
        Farm farm = Farm.builder()
                .name("Farm Name")
                .area(100)
                .location("Farm Location")
                .build();

        // When & Then
        assertThrows(InvalidCredentialsException.class,
                () -> farmService.updateFarm(farmId, farm));

        verify(farmRepository, never()).save(any(Farm.class));
    }
    @Test
    void FarmService_updateFarm_throwsInvalidFarmException() {
        // Given
        UUID farmId = UUID.randomUUID();

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(new Farm()));

        // When & Then
        assertThrows(InvalidFarmException.class, () -> farmService.updateFarm(farmId, null));
        verify(farmRepository, never()).save(any(Farm.class));
    }

    @Test
    void FarmService_deleteFarm_succeed() {
        // Given
        UUID farmId = UUID.randomUUID();
        Farm existingFarm = Farm.builder()
                .id(farmId)
                .name("test farm")
                .area(100)
                .creationDate(LocalDate.now())
                .location("location test")
                .build();

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(existingFarm));

        // When
        farmService.deleteFarm(farmId);

        // Then
        verify(farmRepository).findById(farmId);
        verify(farmRepository).delete(existingFarm);
    }

    @Test
    void FarmService_deleteFarm_throwsInvalidCredentialsException_whenIdIsNull() {
        // When & Then
        assertThrows(InvalidCredentialsException.class,
                () -> farmService.deleteFarm(null));
        verify(farmRepository,never()).findById(any(UUID.class));
        verify(farmRepository, never()).delete(any(Farm.class));
    }

    @Test
    void FarmService_deleteFarm_throwsFarmNotFoundException() {

        // Given
        UUID farmId = UUID.randomUUID();
        when(farmRepository.findById(farmId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(FarmNotFoundException.class,
                () -> farmService.deleteFarm(farmId));
        verify(farmRepository).findById(farmId);
        verify(farmRepository, never()).delete(any(Farm.class));
    }

    @Test
    void FarmService_search_returnsFarms_sendingAllArgs() {

        // Given
        SearchFarmDTO searchFarmDTO = new SearchFarmDTO();
        searchFarmDTO.setName("farmC");
        searchFarmDTO.setLocation("Location A");

        List<Farm> expectedFarms = List.of(
                Farm.builder()
                        .id(UUID.randomUUID())
                        .name("farmC")
                        .location("Location A")
                        .area(100)
                        .creationDate(LocalDate.now())
                        .build()
        );

        when(farmSearchRepository.findByCriteria(searchFarmDTO)).thenReturn(expectedFarms);

        // When
        List<Farm> result = farmService.search(searchFarmDTO);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("farmC", result.get(0).getName());
        verify(farmSearchRepository).findByCriteria(searchFarmDTO);
    }

    @Test
    void FarmService_search_returnsFarms_sendingOnlyName() {

        // Given
        SearchFarmDTO searchFarmDTO = new SearchFarmDTO();
        searchFarmDTO.setName("farm A");

        List<Farm> expectedFarms = List.of(
                Farm.builder()
                        .id(UUID.randomUUID())
                        .name("farm A")
                        .location("Location A")
                        .area(100)
                        .creationDate(LocalDate.now())
                        .build()
        );

        when(farmSearchRepository.findByCriteria(searchFarmDTO)).thenReturn(expectedFarms);

        // When
        List<Farm> result = farmService.search(searchFarmDTO);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("farm A", result.get(0).getName());
        verify(farmSearchRepository).findByCriteria(searchFarmDTO);
    }

    @Test
    void FarmService_search_returnsFarms_sendingOnlyLocation() {

        // Given
        SearchFarmDTO searchFarmDTO = new SearchFarmDTO();
        searchFarmDTO.setLocation("Location A");

        List<Farm> expectedFarms = List.of(
                Farm.builder()
                        .id(UUID.randomUUID())
                        .name("farm A")
                        .location("Location A")
                        .area(100)
                        .creationDate(LocalDate.now())
                        .build()
        );

        when(farmSearchRepository.findByCriteria(searchFarmDTO)).thenReturn(expectedFarms);

        // When
        List<Farm> result = farmService.search(searchFarmDTO);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Location A",result.get(0).getLocation());
        assertEquals("farm A", result.get(0).getName());
        verify(farmSearchRepository).findByCriteria(searchFarmDTO);
    }

    @Test
    void FarmService_search_returnsAllFarms_causedByEmptyCriteria() {

        // Given
        SearchFarmDTO searchFarmDTO = new SearchFarmDTO();
        Farm farm1 = Farm.builder()
                .id(UUID.randomUUID())
                .name("Farm A")
                .location("Location A")
                .build();
        Farm farm2 = Farm.builder()
                .id(UUID.randomUUID())
                .name("Farm B")
                .location("Location B")
                .build();
        List<Farm> expectedFarms = List.of(farm1,farm2);
        when(farmSearchRepository.findByCriteria(searchFarmDTO)).thenReturn(expectedFarms);

        // When
        List<Farm> result = farmService.search(searchFarmDTO);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(farmSearchRepository).findByCriteria(searchFarmDTO);
    }

    @Test
    void FarmService_search_returnsEmptyList() {

        // Given
        SearchFarmDTO searchFarmDTO = new SearchFarmDTO();
        searchFarmDTO.setName("Nonexistent Farm");
        searchFarmDTO.setLocation("Nonexistent Location");

        when(farmSearchRepository.findByCriteria(searchFarmDTO)).thenReturn(Collections.emptyList());

        // When
        List<Farm> result = farmService.search(searchFarmDTO);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(farmSearchRepository).findByCriteria(searchFarmDTO);
    }

    }