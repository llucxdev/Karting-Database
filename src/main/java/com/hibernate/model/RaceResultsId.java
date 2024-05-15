package com.hibernate.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RaceResultsId implements java.io.Serializable {

	private Driver driver;
	private Race race;
	
	@ManyToOne
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	@ManyToOne
	public Race gatRace() {
		return race;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || o.getClass() != o.getClass()) return false;
		
		RaceResultsId that = (RaceResultsId) o;
		
		if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
		if (race != null ? !race.equals(that.race) : that.driver != null) return false;
		
		return true;
	}
	
	public int hashCode() {
		int result;
		result = (driver != null ? driver.hashCode() : 0);
		result = 31 * result + (race != null ? race.hashCode() : 0);
		return result;
	}
}
