//package org.wora.stage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.wora.common.GenericService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class StageServiceImpl implements GenericService<Stage, Long> {
//
//  @Autowired
//  private StageRepository stageRepository;
//
//  @Override
//  public Stage save(Stage stage) {
//    return stageRepository.save(stage);
//  }
//
//  @Override
//  public List<Stage> findAll() {
//    return stageRepository.findAll();
//  }
//
//  @Override
//  public Stage update(Stage stage) {
//    return stageRepository.save(stage);
//  }
//
//  @Override
//  public Optional<Stage> findById(Long id) {
//    return stageRepository.findById(id);
//  }
//}
