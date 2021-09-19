package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bet;
import com.example.demo.model.League;
import com.example.demo.model.Matches;
import com.example.demo.repository.BetRepository;
@Service
@Transactional
public class BetService {
    @Autowired
    private BetRepository betRepository;
   
    public Bet loadByMatch(Matches match, League league) {
		return betRepository.loadByMatch(match, league);
	}
}
