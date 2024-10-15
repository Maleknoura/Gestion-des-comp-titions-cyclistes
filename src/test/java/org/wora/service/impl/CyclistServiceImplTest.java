package org.wora.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Team;
import org.wora.repository.CyclistRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CyclistServiceImplTest {

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    @Mock
    private CyclistRepository cyclistRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCyclist() {
        Team team = new Team();
        team.setName("Team A");

        Cyclist cyclist = new Cyclist();
        cyclist.setFirstName("Noura");
        cyclist.setLastName("Malek");
        cyclist.setDateOfBirth(LocalDate.of(2002, 10, 15));
        cyclist.setNationality("Moroccan");
        cyclist.setTeam(team);

        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);

        Cyclist savedCyclist = cyclistService.save(cyclist);

        assertNotNull(savedCyclist);
        assertEquals("Noura", savedCyclist.getFirstName());
        assertEquals("Malek", savedCyclist.getLastName());
        assertEquals(LocalDate.of(2002, 10, 15), savedCyclist.getDateOfBirth());
        assertEquals("Moroccan", savedCyclist.getNationality());
        assertNotNull(savedCyclist.getTeam());
        assertEquals("Team A", savedCyclist.getTeam().getName());
        verify(cyclistRepository, times(1)).save(cyclist);
    }


}
