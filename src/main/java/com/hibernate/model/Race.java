package com.hibernate.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="race")
public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int race_id;
	private LocalDate date;
	private int laps;
	
	public Race() {
		super();
	}

	public Race(LocalDate date, int laps) {
		super();
		this.date = date;
		this.laps = laps;
	}

	public int getRace_id() {
		return race_id;
	}

	public void setRace_id(int race_id) {
		this.race_id = race_id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}
}
