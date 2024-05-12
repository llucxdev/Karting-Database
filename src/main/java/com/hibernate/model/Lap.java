package com.hibernate.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lap {
	private int lap_id;
    private int driver_id;
    private LocalTime time;
    private LocalDate date;
    
    public Lap() {
    	super();
    }
    
	public Lap(int lap_id, int driver_id, LocalTime time, LocalDate date) {
		super();
		this.lap_id = lap_id;
		this.driver_id = driver_id;
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

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
