package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Stage;
import org.wora.Entity.Team;
import org.wora.Entity.embeddebals.StageResult;
import org.wora.config.AppConfig;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.CyclistRepository;
import org.wora.repository.StageRepository;
import org.wora.repository.TeamRepository;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
        StageRepository stageRepository = context.getBean(StageRepository.class);
        CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);
        TeamRepository teamRepository = context.getBean(TeamRepository.class);

        Competition competition = new Competition();
        competition.setName("Tour de France");
        competition.setStartDate(LocalDate.of(2024, 1, 6));
        competition.setEndDate(LocalDate.of(2025, 1, 6));
        competition.setLocation("Marrakech");
        competitionRepository.save(competition);

        Stage stage = new Stage();
        stage.setName("Stage 1");
        stage.setDistance(200.0);
        stage.setDate(LocalDate.of(2024, 7, 1));
        stage.setCompetition(competition);
        stageRepository.save(stage);

        Team team = new Team();
        team.setName("firstTeam");
        teamRepository.save(team);

        Cyclist cyclist = new Cyclist();
        cyclist.setFirstName("John");
        cyclist.setLastName("Doe");
        cyclist.setDateOfBirth(LocalDate.of(1990, 7, 1));
        cyclist.setNationality("French");

        team.setId(1L);
        cyclist.setTeam(team);


        cyclistRepository.save(cyclist);

//        StageResult stageResult = new StageResult();
//        stageResult.setCyclist(cyclist);
//        stageResult.setStage(stage);
//        stageResult.setDetails(120.5);
//        stageRepository.save(stageResult);

        System.out.println("Entities saved to the database!");
    }
}
