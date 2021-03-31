package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contest;
import com.example.demo.repository.ContestRepository;
@Service
@Transactional
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;
    public List<Contest> listAllContest() {
        return contestRepository.findAll();
    }

    public void saveContest(Contest user) {
    	contestRepository.save(user);
    }

    public Contest getContest(Integer id) {
        return contestRepository.findById(id).get();
    }

    public void deleteContest(Integer id) {
    	contestRepository.deleteById(id);
    }
    

}
