package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Match {

	private int id;
	private LocalDate date;
	private String homeTeam;
	private String awayTeam;
	private int fthg; // full time home goals
	private int ftag; // full time away goals
	private String ftr; // full time result (H, A, D)
	// E' possibile aggiungere altri campi, se risulteranno necessari

	/**
	 * New match
	 * 
	 * @param id
	 * @param season
	 * @param div
	 * @param date
	 * @param homeTeam
	 * @param awayTeam
	 * @param fthg
	 * @param ftag
	 * @param ftr
	 */
	public Match(int id, LocalDate date, String homeTeam, String awayTeam, int fthg, int ftag, String ftr) {
		super();
		this.id = id;
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.fthg = fthg;
		this.ftag = ftag;
		this.ftr = ftr;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the homeTeam
	 */
	public String getHomeTeam() {
		return homeTeam;
	}

	/**
	 * @return the awayTeam
	 */
	public String getAwayTeam() {
		return awayTeam;
	}

	/**
	 * @return the fthg
	 */
	public int getFthg() {
		return fthg;
	}

	/**
	 * @return the ftag
	 */
	public int getFtag() {
		return ftag;
	}

	/**
	 * @return the ftr
	 */
	public String getFtr() {
		return ftr;
	}

	/**
	 * @param id
	 * the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @param date
	 * the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param homeTeam
	 * the homeTeam to set
	 */
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * @param awayTeam
	 * the awayTeam to set
	 */
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	/**
	 * @param fthg
	 * the fthg to set
	 */
	public void setFthg(int fthg) {
		this.fthg = fthg;
	}

	/**
	 * @param ftag
	 * the ftag to set
	 */
	public void setFtag(int ftag) {
		this.ftag = ftag;
	}

	/**
	 * @param ftr
	 * the ftr to set
	 */
	public void setFtr(String ftr) {
		this.ftr = ftr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

