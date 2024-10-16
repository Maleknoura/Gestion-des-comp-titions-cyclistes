    package org.wora.service.impl;

    import jakarta.validation.ConstraintViolationException;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Disabled;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.wora.Entity.Cyclist;
    import org.wora.Entity.Team;
    import org.wora.repository.CyclistRepository;

    import java.time.LocalDate;
    import java.util.Arrays;
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


            Cyclist cyclist = new Cyclist("Noura","Malek",LocalDate.of(2002,10,15),"moroccan",team);

            when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);

            Cyclist savedCyclist = cyclistService.save(cyclist);

            assertNotNull(savedCyclist);
            assertEquals("Noura", savedCyclist.getFirstName());
            assertEquals("Malek", savedCyclist.getLastName());
            assertEquals(LocalDate.of(2002, 10, 15), savedCyclist.getDateOfBirth());
            assertEquals("moroccan", savedCyclist.getNationality());
            assertNotNull(savedCyclist.getTeam());
            assertEquals("Team A", savedCyclist.getTeam().getName());
            verify(cyclistRepository, times(1)).save(cyclist);

        }


        @Test
        @DisplayName("Should throw exception when repository fails to save")
        void save_ShouldThrowException_WhenRepositoryFails() {
            Team team = new Team("Team A");
            Cyclist cyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", team);

            when(cyclistRepository.save(any(Cyclist.class))).thenThrow(new RuntimeException("Database error"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                cyclistService.save(cyclist);
            });

            assertEquals("Database error", exception.getMessage());
            verify(cyclistRepository, times(1)).save(cyclist);
        }




    }
