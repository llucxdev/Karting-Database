package com.hibernate.model;

import java.time.LocalDate;
import java.util.List;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int team_id;
	private String name;
	private LocalDate date;
	private Blob img;
	@Transient
	private List<Driver> drivers;
	
	public Team() {
		super();
	}

	public Team(String name, LocalDate date, Blob img) {
		this.name = name;
		this.date = date;
		this.img = img;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Blob getImg() {
		return img;
	}

	public void setImg(Blob img2) {
		this.img = img2;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	
}
