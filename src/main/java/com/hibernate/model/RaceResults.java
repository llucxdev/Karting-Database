package com.hibernate.model;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "race_results")
@AssociationOverrides({
	@AssociationOverride(name = "pk.driverk", 
		joinColumns = @JoinColumn(name = "driver_id")),
	@AssociationOverride(name = "pk.race", 
		joinColumns = @JoinColumn(name = "race_id")) })
public class RaceResults implements java.io.Serializable {
	
	private RaceResultsId pk = new RaceResultsId();
	private int position;
	
	public RaceResults() {
		super();
	}
	
	@EmbeddedId
	public RaceResultsId getPk() {
		return pk;
	}
	
	public void setPk(RaceResultsId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Driver getDriver() {
		return getPk().getDriver();
	}
	
	public void setDriver(Driver driver) {
		getPk().setDriver(driver);
	}
	
	@Transient
	public Race getRace() {
		return getPk().gatRace();
	}
	
	public void setRace(Race race) {
		getPk().setRace(race);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		RaceResults that = (RaceResults) o;
		
		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;
		
		return true;
	}
	
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}