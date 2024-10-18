package org.wora.service.Api;

import org.wora.Entity.Stage;

import java.util.List;
import java.util.Optional;

public interface StageService {
    Stage save(Stage stage);
    List<Stage>findall();
    Stage update(Stage stage);
    Optional<Stage> findById(long id);
}
