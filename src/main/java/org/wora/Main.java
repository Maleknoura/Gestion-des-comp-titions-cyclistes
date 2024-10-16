package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Stage;
import org.wora.Entity.Team;
import org.wora.config.AppConfig;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.CyclistRepository;
import org.wora.repository.StageRepository;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
        StageRepository stageRepository = context.getBean(StageRepository.class);
        CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);

        Competition competition = new Competition();
        competition.setName("Tour de France");
        competition.setStartDate(LocalDate.of(2024,01,06));
        competition.setEndDate(LocalDate.of(2025,01,06));
        competitionRepository.save(competition);

        Stage stage = new Stage();
        stage.setName("Stage 1");
        stage.setDistance(200.0);
        stage.setDate(LocalDate.of(2024, 7, 1));
        stage.setCompetition(competition);
        stageRepository.save(stage);

        Cyclist cyclist = new Cyclist();
        cyclist.setFirstName("john");
        cyclist.setFirstName("Doe");
        cyclist.setDateOfBirth(LocalDate.of(2024, 7, 1));
        cyclist.setNationality("French");

        cyclistRepository.save(cyclist);

        System.out.println("Entities saved to the database!");
    }    }

