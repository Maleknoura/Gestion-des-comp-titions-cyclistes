//package org.wora.team;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.wora.common.GenericService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TeamServiceImpl implements GenericService<Team, Long> {
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @Override
//    public Team save(Team team) {
//        return teamRepository.save(team);
//    }
//
//    @Override
//    public List<Team> findAll() {
//        return teamRepository.findAll();
//    }
//
//    @Override
//    public Team update(Team team) {
//        return teamRepository.save(team);
//    }
//
//    @Override
//    public Optional<Team> findById(Long aLong) {
//        return teamRepository.findById(aLong);
//    }
//
//
//
//
//}
