package org.wora.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.generalResult.GeneralResult;
import org.wora.generalResult.GeneralResultRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wora.common.GenericService;

@Service
public class CompetitionServiceImpl implements GenericService<Competition,Long> {
    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private GeneralResultRepository generalResultRepository;
    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);



    public Competition save( Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Optional<Competition> findById(Long id) {
        logger.info("logger initialized"+id);
        return competitionRepository.findById(id);
    }

    @Override
    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
    }


    public List<Competition> findByDateRange(LocalDate startDate, LocalDate endDate) {
//        logger.info("logger initialized."+startDate);
        return competitionRepository.findByStartDateBetween(startDate, endDate);

    }


    public List<Competition> findByLocation(String location) {
        return competitionRepository.findByLocation(location);
    }


    public List<GeneralResult> findCyclistRankings(Long competitionId) {
        return generalResultRepository.findByCompetitionIdOrderByGeneralRankAsc(competitionId);
    }
}

