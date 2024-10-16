package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.Entity.Stage;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.StageRepository;
import org.wora.service.StageService;

@Service
public class StageServiceImpl implements StageService {
  @Autowired
    private StageRepository stageRepository;
    @Autowired
    private CompetitionRepository competitionRepository;

  @Override
  public Stage save(Stage stage) {
    return stageRepository.save(stage);
  }
}
