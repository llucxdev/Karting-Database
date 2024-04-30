package com.hibernate.model;

import java.sql.Blob;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="driver")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int driver_id;
	String name;
	LocalDate dob;
	int age;
	int laps;
	int races;
	int podiums;
	int wins;
	int team;
	int kart;
	
	public Driver() {
		
	}

	public Driver(int driver_id, String name, LocalDate dob, int age, int laps, int races, int podiums, int wins,
			int team, int kart) {
		this.driver_id = driver_id;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.laps = laps;
		this.races = races;
		this.podiums = podiums;
		this.wins = wins;
		this.team = team;
		this.kart = kart;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public int getRaces() {
		return races;
	}

	public void setRaces(int races) {
		this.races = races;
	}

	public int getPodiums() {
		return podiums;
	}

	public void setPodiums(int podiums) {
		this.podiums = podiums;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getKart() {
		return kart;
	}

	public void setKart(int kart) {
		this.kart = kart;
	}
	
}
