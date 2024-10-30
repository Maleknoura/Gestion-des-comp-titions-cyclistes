package org.wora.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wora.cyclist.Cyclist;
import org.wora.cyclist.dto.CyclistRequestDto;
import org.wora.cyclist.dto.CyclistResponseDto;
import org.wora.team.Team;
import org.wora.cyclist.CyclistRepository;
import org.wora.cyclist.CyclistServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CyclistServiceImplTest {

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    @Mock
    private CyclistRepository cyclistRepository;

    @Test
    @DisplayName("Should return saved cyclist when given valid data")
    void save_ShouldReturnCreatedCyclist_WhenGivenValidData() {
        Team team = new Team("Team A");
        CyclistRequestDto cyclistRequestDto = new CyclistRequestDto(
                "Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan"
        );

        Cyclist cyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", team);

        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);

        CyclistResponseDto savedCyclistDto = cyclistService.save(cyclistRequestDto);

        assertNotNull(savedCyclistDto);
        assertEquals("Noura", savedCyclistDto.firstName());
        assertEquals("Malek", savedCyclistDto.lastName());
        assertEquals(LocalDate.of(2002, 10, 15), savedCyclistDto.dateOfBirth());
        assertEquals("moroccan", savedCyclistDto.nationality());

        verify(cyclistRepository, times(1)).save(any(Cyclist.class));
    }

    @Test
    @DisplayName("Should return all cyclists")
    void findAll_ShouldReturnListOfCyclists() {
        Cyclist cyclist1 = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", new Team("Team A"));
        Cyclist cyclist2 = new Cyclist("John", "Doe", LocalDate.of(1990, 1, 1), "american", new Team("Team B"));

        when(cyclistRepository.findAll()).thenReturn(List.of(cyclist1, cyclist2));

        List<CyclistResponseDto> cyclists = cyclistService.findAll();

        assertNotNull(cyclists);
        assertEquals(2, cyclists.size());
        assertEquals("Noura", cyclists.get(0).firstName());
        assertEquals("John", cyclists.get(1).firstName());

        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no cyclists exist")
    void findAll_ShouldReturnEmptyList_WhenNoCyclistsExist() {
        when(cyclistRepository.findAll()).thenReturn(Collections.emptyList());

        List<CyclistResponseDto> cyclists = cyclistService.findAll();

        assertNotNull(cyclists);
        assertTrue(cyclists.isEmpty());

        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return updated cyclist when given valid data")
    void update_ShouldReturnUpdatedCyclist_WhenGivenValidData() {
        Team team = new Team("Team A");
        Cyclist existingCyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", team);
        CyclistRequestDto cyclistRequestDto = new CyclistRequestDto(
                "Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan"
        );

        when(cyclistRepository.findById(1L)).thenReturn(Optional.of(existingCyclist));
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(existingCyclist);

        CyclistResponseDto updatedCyclistDto = cyclistService.update(cyclistRequestDto, 1L);

        assertNotNull(updatedCyclistDto);
        assertEquals("Noura", updatedCyclistDto.firstName());

        verify(cyclistRepository, times(1)).findById(1L);
        verify(cyclistRepository, times(1)).save(any(Cyclist.class));
    }


    @Test
    @DisplayName("Should return cyclist when found by ID")
    void findById_ShouldReturnCyclist_WhenCyclistExists() {
        Team team = new Team("Team A");
        Cyclist cyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", team);

        when(cyclistRepository.findById(1L)).thenReturn(Optional.of(cyclist));

        Optional<CyclistResponseDto> foundCyclistDto = cyclistService.findById(1L);

        assertTrue(foundCyclistDto.isPresent());
        assertEquals("Noura", foundCyclistDto.get().firstName());

        verify(cyclistRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should return empty Optional when cyclist not found by ID")
    void findById_ShouldReturnEmptyOptional_WhenCyclistDoesNotExist() {
        when(cyclistRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<CyclistResponseDto> foundCyclistDto = cyclistService.findById(1L);

        assertFalse(foundCyclistDto.isPresent());

        verify(cyclistRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should delete cyclist when given valid ID")
    void deleteById_ShouldDeleteCyclist_WhenCyclistExists() {
        Cyclist cyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", new Team("Team A"));

        when(cyclistRepository.findById(1L)).thenReturn(Optional.of(cyclist));

        assertDoesNotThrow(() -> cyclistService.deleteById(1L));

        verify(cyclistRepository, times(1)).findById(1L);
        verify(cyclistRepository, times(1)).delete(cyclist);
    }

    @Test
    @DisplayName("Should throw RuntimeException when deleting a non-existing cyclist")
    void deleteById_ShouldThrowRuntimeException_WhenCyclistDoesNotExist() {
        when(cyclistRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cyclistService.deleteById(1L));

        verify(cyclistRepository, times(1)).findById(anyLong());
    }
}
