//    package org.wora.service.impl;
//
//    import org.junit.jupiter.api.DisplayName;
//    import org.junit.jupiter.api.Test;
//    import org.junit.jupiter.api.extension.ExtendWith;
//    import org.mockito.InjectMocks;
//    import org.mockito.Mock;
//    import org.mockito.junit.jupiter.MockitoExtension;
//    import org.wora.cyclist.Cyclist;
//    import org.wora.team.Team;
//    import org.wora.cyclist.CyclistRepository;
//    import org.wora.cyclist.CyclistServiceImpl;
//
//    import java.time.LocalDate;
//
//    import static org.junit.jupiter.api.Assertions.*;
//    import static org.mockito.Mockito.*;
//
//    @ExtendWith(MockitoExtension.class)
//    class CyclistServiceImplTest {
//
//        @InjectMocks
//        private CyclistServiceImpl cyclistService;
//
//        @Mock
//        private CyclistRepository cyclistRepository;
//
//        @Test
//        @DisplayName("Should return saved cyclist when given valid data")
//        void save_ShouldReturnCreatedCyclist_WhenGivenValidData() {
//            Team team = new Team("Team A");
//
//
//            Cyclist cyclist = new Cyclist("Noura","Malek",LocalDate.of(2002,10,15),"moroccan",team);
//
//            when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);
//
//            Cyclist savedCyclist = cyclistService.save(cyclist);
//
//            assertNotNull(savedCyclist);
//            assertEquals("Noura", savedCyclist.getFirstName());
//            assertEquals("Malek", savedCyclist.getLastName());
//            assertEquals(LocalDate.of(2002, 10, 15), savedCyclist.getDateOfBirth());
//            assertEquals("moroccan", savedCyclist.getNationality());
//            assertNotNull(savedCyclist.getTeam());
//            assertEquals("Team A", savedCyclist.getTeam().getName());
//            verify(cyclistRepository, times(1)).save(cyclist);
//
//        }
//
//
//        @Test
//        @DisplayName("Should throw exception when repository fails to save")
//        void save_ShouldThrowException_WhenRepositoryFails() {
//            Team team = new Team("Team A");
//            Cyclist cyclist = new Cyclist("Noura", "Malek", LocalDate.of(2002, 10, 15), "moroccan", team);
//
//            when(cyclistRepository.save(any(Cyclist.class))).thenThrow(new RuntimeException("Database error"));
//
//            Exception exception = assertThrows(RuntimeException.class, () -> {
//                cyclistService.save(cyclist);
//            });
//
//            assertEquals("Database error", exception.getMessage());
//            verify(cyclistRepository, times(1)).save(cyclist);
//        }
//
//
//
//
//    }
