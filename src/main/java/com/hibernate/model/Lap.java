package com.hibernate.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lap")
public class Lap {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int lap_id;
    private int driver_id;
    private int kart_id;
    private Timestamp time;
    private LocalDate date;
    
    public Lap() {
    	super();
    }
    
	public Lap(int driver_id, int kart_id, Timestamp time, LocalDate date) {
		super();
		this.driver_id = driver_id;
		this.kart_id = kart_id;
		this.time = time;
		this.date = date;
	}

	public int getLap_id() {
		return lap_id;
	}

	public void setLap_id(int lap_id) {
		this.lap_id = lap_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getKart_id() {
		return kart_id;
	}

	public void setKart_id(int kart_id) {
		this.kart_id = kart_id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
