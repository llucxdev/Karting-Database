package com.hibernate.model;

import java.sql.Blob;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Driver")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int driver_id;
	
	String name;
	String last_name;
	LocalDate birthdate;
	int age;
	String nationality;
	int number;
	int races;
	int podiums;
	int wins;
	int fast_laps;
	int poles;
	int dnf;
	int points;
	//int team;
	//int points;
	//Blob img;
	
	public Driver() {
		
	}

	public Driver(String name, String last_name, LocalDate birthdate, String nationality, int number, int races,
			int podiums, int wins, int fast_laps, int poles, int dnf, int points) {
		this.name = name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.number = number;
		this.races = races;
		this.podiums = podiums;
		this.wins = wins;
		this.fast_laps = fast_laps;
		this.poles = poles;
		this.dnf = dnf;
		this.points = points;
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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public int getFast_laps() {
		return fast_laps;
	}

	public void setFast_laps(int fast_laps) {
		this.fast_laps = fast_laps;
	}

	public int getPoles() {
		return poles;
	}

	public void setPoles(int poles) {
		this.poles = poles;
	}

	public int getDnf() {
		return dnf;
	}

	public void setDnf(int dnf) {
		this.dnf = dnf;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
