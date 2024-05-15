package com.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "race_results")
public class RaceResults {
	
	private int race;
	private int driver;
	private int position;
	
	public RaceResults() {
		super();
	}
	
	public RaceResults(int race, int driver, int position) {
		this.race = race;
		this.driver = driver;
		this.position = position;
	}
	
	public int getRace() {
		return race;
	}
	
	public void setRace(int race) {
		this.race = race;
	}
	
	public int getDriver() {
		return driver;
	}
	
	public void setDriver(int driver) {
		this.driver = driver;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}