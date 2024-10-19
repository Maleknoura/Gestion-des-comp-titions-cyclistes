package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.Entity.*;
import org.wora.Entity.embeddebals.GeneralResultId;
import org.wora.config.AppConfig;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.CyclistRepository;
import org.wora.repository.GeneralResultRepository;
import org.wora.repository.StageRepository;
import org.wora.service.Api.GeneralResultService;

import java.time.Duration;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
}
}
