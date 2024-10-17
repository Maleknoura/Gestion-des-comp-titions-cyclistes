package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Stage;
import org.wora.Entity.Team;
import org.wora.Entity.embeddebals.GeneralResult;
import org.wora.Entity.embeddebals.GeneralResultId;
import org.wora.config.AppConfig;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.CyclistRepository;
import org.wora.repository.GeneralResultRepository;
import org.wora.repository.StageRepository;
import org.wora.repository.TeamRepository;
import org.wora.service.GeneralResultService;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GeneralResultService generalResultService = context.getBean(GeneralResultService.class);

        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
        StageRepository stageRepository = context.getBean(StageRepository.class);
        CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);
        TeamRepository teamRepository = context.getBean(TeamRepository.class);
        GeneralResultRepository generalResultRepository = context.getBean(GeneralResultRepository.class);

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
        cyclist.setTeam(team);
        cyclistRepository.save(cyclist);

        try {
            GeneralResult result = generalResultService.save(cyclist.getId(), competition.getId());
            System.out.println("GeneralResult sauvegardé avec succès. ID: " + result.getId());
        } catch (RuntimeException e) {
            System.out.println("Erreur lors de la sauvegarde du GeneralResult: " + e.getMessage());
        }

        GeneralResult savedResult = generalResultRepository.findById(new GeneralResultId(cyclist.getId(), competition.getId())).orElse(null);
        if (savedResult != null) {
            System.out.println("GeneralResult trouvé dans la base de données.");
            System.out.println("Cycliste: " + savedResult.getCyclist().getFirstName());
            System.out.println("Compétition: " + savedResult.getCompetition().getName());
        } else {
            System.out.println("GeneralResult non trouvé dans la base de données.");
        }

        try {
            long cyclistId = 5;
            long competitionId = 9;
            generalResultService.removeCyclistFromCompetition(cyclistId, competitionId);
            System.out.println("Cycliste supprimé de la compétition avec succès.");
        } catch (RuntimeException e) {
            System.out.println("Erreur lors de la suppression du cycliste de la compétition: " + e.getMessage());
        }

        System.out.println("Entities saved to the database and cyclist registered to competition!");
    }
}
