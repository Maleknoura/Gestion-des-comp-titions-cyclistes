//package org.wora.generalResult;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.wora.generalResult.embeddabls.GeneralResultId;
//import org.wora.stageResult.embeddabls.StageResultId;
//import org.wora.competition.CompetitionRepository;
//import org.wora.cyclist.Cyclist;
//import org.wora.cyclist.CyclistRepository;
//import org.wora.common.GenericService;
//import org.wora.stage.Stage;
//import org.wora.stage.StageRepository;
//import org.wora.stageResult.StageResult;
//import org.wora.stageResult.StageResultRepository;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GeneralResultServiceImpl implements GenericService<GeneralResult, GeneralResultId> {
//
//    @Autowired
//    private GeneralResultRepository generalResultRepository;
//
//    @Autowired
//    private CyclistRepository cyclistRepository;
//
//    @Autowired
//    private CompetitionRepository competitionRepository;
//
//    @Autowired
//    private StageRepository stageRepository;
//
//    @Autowired
//    private StageResultRepository stageResultRepository;
//
//    @Transactional
//    public StageResult save(long cyclistId, long stageId, Duration time, Integer rank) {
//        Cyclist cyclist = cyclistRepository.findById(cyclistId)
//                .orElseThrow(() -> new RuntimeException("Cyclist not found"));
//        Stage stage = stageRepository.findById(stageId)
//                .orElseThrow(() -> new RuntimeException("Stage not found"));
//
//        StageResultId id = new StageResultId(cyclistId, stageId);
//        StageResult stageResult = new StageResult(id, cyclist, stage, time, rank);
//
//        return stageResultRepository.save(stageResult);
//    }
//
//    @Override
//    @Transactional
//    public GeneralResult save(GeneralResult entity) {
//        return generalResultRepository.save(entity);
//    }
//
//    @Transactional
//    public void removeCyclistFromCompetition(long cyclistId, long competitionId) {
//        GeneralResultId generalResultId = new GeneralResultId(cyclistId, competitionId);
//        Optional<GeneralResult> generalResult = generalResultRepository.findById(generalResultId);
//        if (generalResult.isPresent()) {
//            generalResultRepository.delete(generalResult.get());
//        } else {
//            throw new RuntimeException("Registration not found for this cyclist and competition");
//        }
//    }
//
//    public List<GeneralResult> findByCompetitionIdOrderByGeneralRankAsc(Long competitionId) {
//        return generalResultRepository.findByCompetitionIdOrderByGeneralRankAsc(competitionId);
//    }
//
//    @Override
//    public List<GeneralResult> findAll() {
//        return generalResultRepository.findAll();
//    }
//
//    @Override
//    public Optional<GeneralResult> findById(GeneralResultId id) {
//        return generalResultRepository.findById(id);
//    }
//
//    @Override
//    public GeneralResult update(GeneralResult entity) {
//        if (!generalResultRepository.existsById(entity.getId())) {
//            throw new RuntimeException("GeneralResult not found");
//        }
//        return generalResultRepository.save(entity);
//    }
//}
