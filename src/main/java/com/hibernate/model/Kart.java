package com.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="kart")
public class Kart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int kart_id;
	private boolean available;
	
	public Kart() {
		super();
	}
	
	public Kart(boolean available) {
		this.available = available;
	}

	public int getKart_id() {
		return kart_id;
	}

	public void setKart_id(int kart_id) {
		this.kart_id = kart_id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}