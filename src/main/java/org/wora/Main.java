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
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GeneralResultService generalResultService = context.getBean(GeneralResultService.class);

        List<GeneralResult> results = generalResultService.findByCompetitionIdOrderByGeneralRankAsc(9L);

        if (!results.isEmpty()) {
            Competition competition = results.get(0).getCompetition();
            System.out.println("Competition: " + competition.getName());

            for (GeneralResult result : results) {
                System.out.println("Cyclist: " + result.getCyclist().getFirstName() + ", Rank: " + result.getGeneralRank());
            }
        } else {
            System.out.println("No results found for the given competition.");
        }

    }
}

