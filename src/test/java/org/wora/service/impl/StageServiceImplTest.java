//package org.wora.service.impl;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.wora.stage.StageRepository;
//import org.wora.stage.StageServiceImpl;
//
//import static org.mockito.ArgumentMatchers.any;
//
//@ExtendWith(MockitoExtension.class)
//class StageServiceImplTest {
//
//    @InjectMocks
//    private StageServiceImpl stageService;
//
//    @Mock
//    private StageRepository stageRepository;

//    @Test
//    @DisplayName("Should return saved stage with result")
//    void save_ShouldReturnSavedStage_WithValidData() {
//
//        Stage stage = new Stage("firstStage", 600.00, LocalDate.of(2024, 12, 1));
//        when(stageRepository.save(any(Stage.class))).thenReturn(stage);
//
//
//        Stage savedStage = stageService.save(stage);
//
//
//        assertNotNull(savedStage);
//        assertEquals("firstStage", savedStage.getName());
//        assertEquals(600.00, savedStage.getDistance());
//        assertEquals(LocalDate.of(2024, 12, 1), savedStage.getDate());
//    }

//}
