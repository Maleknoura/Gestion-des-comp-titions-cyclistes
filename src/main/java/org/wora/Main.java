package org.wora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wora.Entity.*;
import org.wora.config.AppConfig;
import org.wora.repository.StageResultRepository;
import org.wora.service.Api.CompetitionService;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CompetitionService competitionService = context.getBean(CompetitionService.class);
        StageResultRepository stageResultRepository = context.getBean(StageResultRepository.class);

        Long competitionId = 9L;
        Optional<Competition> competitionOpt = competitionService.findById(competitionId);

        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            System.out.println("Competition: " + competition.getName());
            System.out.println("Location: " + competition.getLocation());
            System.out.println("Rankings:");

            List<GeneralResult> results = competitionService.findCyclistRankings(competitionId);

            for (GeneralResult result : results) {
                String formattedTime = formatDuration(result.getGeneralTime());
                System.out.println("Cyclist: " + result.getCyclist().getFirstName() + " " + result.getCyclist().getLastName() +
                        ", Rank: " + result.getGeneralRank() + ", General Time: " + formattedTime);

                List<StageResult> stageResults = stageResultRepository.findByCyclistIdAndStageCompetitionId(result.getCyclist().getId(), competitionId);
                for (StageResult stageResult : stageResults) {
                    System.out.println("  Stage: " + stageResult.getStage().getName() +
                            ", Location: " + competition.getLocation() +
                            ", Time: " + formatDuration(stageResult.getTime()));
                }
            }
        } else {
            System.out.println("Competition not found.");
        }
    }

    private static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;
        seconds %= 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
