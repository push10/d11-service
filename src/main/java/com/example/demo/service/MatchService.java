package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Matches;
import com.example.demo.repository.MatchRepository;

@Service
@Transactional
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    public List<Matches> listAllMatches() {
        return matchRepository.findAll();
    }

    public void saveMatch(Matches match) {
    	matchRepository.save(match);
    }

    public Matches getMatch(Integer id) {
        return matchRepository.findById(id).get();
    }

    public void deleteMatch(Integer id) {
    	matchRepository.deleteById(id);
    }
    
    public List<Matches> loadByContest(int contestId) {
        return matchRepository.loadByContest(contestId);
    }
    
}
