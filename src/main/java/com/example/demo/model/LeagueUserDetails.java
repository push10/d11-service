package com.example.demo.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "league_user_match_details")
public class LeagueUserDetails {
	private int id;
	private League league;
	private User user;
	private Matches match; 
	
	private boolean isPaymentDone;
	private User winnerUser;
	
	private List<User> allUsersForLeague;
	
	private List<String> nonPaidUsers; 
	
	private Map<String, UserInvestmentDetails> userInvestementDetails;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Access(AccessType.PROPERTY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	@Access(AccessType.PROPERTY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Access(AccessType.PROPERTY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "match")
	public Matches getMatch() {
		return match;
	}


	public void setMatch(Matches match) {
		this.match = match;
	}

	public boolean isPaymentDone() {
		return isPaymentDone;
	}

	public void setPaymentDone(boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}


	
	@Access(AccessType.PROPERTY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "winnerUser")
	public User getWinnerUser() {
		return winnerUser;
	}

	public void setWinnerUser(User winnerUser) {
		this.winnerUser = winnerUser;
	}

	@Transient
	public List<User> getAllUsersForLeague() {
		return allUsersForLeague;
	}

	public void setAllUsersForLeague(List<User> allUsersForLeague) {
		this.allUsersForLeague = allUsersForLeague;
	}

	@Transient
	public List<String> getNonPaidUsers() {
		return nonPaidUsers;
	}

	public void setNonPaidUsers(List<String> nonPaidUsers) {
		this.nonPaidUsers = nonPaidUsers;
	}

	@Transient
	public Map<String, UserInvestmentDetails> getUserInvestementDetails() {
		return userInvestementDetails;
	}

	public void setUserInvestementDetails(Map<String, UserInvestmentDetails> userInvestementDetails) {
		this.userInvestementDetails = userInvestementDetails;
	}

	
	
	

}
