package com.example.demo.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "league")
public class League {
	private int id;
    private String joiningCode;
    private Contest contest;

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJoiningCode() {
		return joiningCode;
	}

	public void setJoiningCode(String joiningCode) {
		this.joiningCode = joiningCode;
	}

	@Access(AccessType.PROPERTY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="contest")
	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	
	
	private List<User> user;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "league_users",
	        joinColumns = {
	                @JoinColumn(name = "league",
	                        nullable = false,
	                        updatable = false) },
	        inverseJoinColumns = {
	                @JoinColumn(name = "user",
	                        nullable = false,
	                        updatable = false) })
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
	
	
    
}
