package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.Entity.Stage;
import org.wora.repository.StageRepository;
import org.wora.service.Api.StageService;

import java.util.List;
import java.util.Optional;

@Service
public class StageServiceImpl implements StageService {
  @Autowired
    private StageRepository stageRepository;


  @Override
  public Stage save(Stage stage) {
    return stageRepository.save(stage);
  }

  @Override
  public List<Stage> findall() {
    return stageRepository.findAll();
  }

  @Override
  public Stage update(Stage stage) {
    return stageRepository.save(stage);
  }

  @Override
  public Optional<Stage> findById(long id) {
    return stageRepository.findById(id);
  }
}
