package com.example.demo.model;

public class UserInvestmentDetails {
	private int totalInvestmentAmt;
	private int winningAmt;

	public UserInvestmentDetails(int totalInvestmentAmt, int winningAmt) {
		super();
		this.totalInvestmentAmt = totalInvestmentAmt;
		this.winningAmt = winningAmt;
	}

	public UserInvestmentDetails(int totalInvestmentAmt) {
		super();
		this.totalInvestmentAmt = totalInvestmentAmt;
	}

	public UserInvestmentDetails() {
		super();
	}

	public int getTotalInvestmentAmt() {
		return totalInvestmentAmt;
	}

	public void setTotalInvestmentAmt(int totalInvestmentAmt) {
		this.totalInvestmentAmt = totalInvestmentAmt;
	}

	public int getWinningAmt() {
		return winningAmt;
	}

	public void setWinningAmt(int winningAmt) {
		this.winningAmt = winningAmt;
	}

}
