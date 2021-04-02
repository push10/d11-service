package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.League;
import com.example.demo.model.User;
import com.example.demo.repository.LeagueRepository;
import com.example.demo.repository.UserRepository;
@Service
@Transactional
public class LeagueService {
    @Autowired
    private LeagueRepository leagueRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<League> listAllLeague() {
        return leagueRepository.findAll();
    }

    public void saveLeague(League league) {
    	leagueRepository.save(league);
    }

    public League getLeague(Integer id) {
        return leagueRepository.findById(id).get();
    }
    
    public League getLeagueByCode(String code) {
        return leagueRepository.findByCode(code);
    }

    public void deleteLeague(Integer id) {
    	leagueRepository.deleteById(id);
    }
    
    public void joinLeague(Integer userId, String code) {
    	League league = getLeagueByCode(code);
    	Optional<User> user = userRepository.findById(userId);
    	if(league != null && user.isPresent()) {
    		leagueRepository.joinContestByCode(league.getId(), userId);
    	}
    }

}
