package com.hibernate.model;

import java.sql.Blob;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a Driver in the database.
 * Mapped to the "driver" table.
 */
@Entity
@Table(name="driver")
public class Driver {

    /**
     * Unique identifier for the driver.
     * Automatically generated using a sequence strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int driver_id;

    /**
     * Name of the driver.
     */
    private String name;

    /**
     * Date of birth of the driver.
     */
    private LocalDate dob;

    /**
     * Age of the driver.
     */
    private int age;

    /**
     * Number of laps completed by the driver.
     */
    private int laps;

    /**
     * Number of races participated in by the driver.
     */
    private int races;

    /**
     * Number of podium finishes by the driver.
     */
    private int podiums;

    /**
     * Number of wins achieved by the driver.
     */
    private int wins;

    /**
     * Identifier for the team the driver is associated with.
     */
    private int team;

    /**
     * Identifier for the kart used by the driver.
     */
    private int kart;

    /**
     * Image of the driver stored as a Blob.
     */
    private Blob img;

    /**
     * Default constructor.
     */
    public Driver() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Driver object.
     *
     * @param name     Name of the driver.
     * @param dob      Date of birth of the driver.
     * @param age      Age of the driver.
     * @param laps     Number of laps completed by the driver.
     * @param races    Number of races participated in by the driver.
     * @param podiums  Number of podium finishes by the driver.
     * @param wins     Number of wins achieved by the driver.
     * @param img      Image of the driver stored as a Blob.
     */
    public Driver(String name, LocalDate dob, int age, int laps, int races, int podiums, int wins, Blob img) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.laps = laps;
        this.races = races;
        this.podiums = podiums;
        this.wins = wins;
        this.img = img;
    }

    /**
     * Gets the driver ID.
     *
     * @return driver_id
     */
    public int getDriver_id() {
        return driver_id;
    }

    /**
     * Sets the driver ID.
     *
     * @param driver_id the new driver ID
     */
    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    /**
     * Gets the name of the driver.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the driver.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date of birth of the driver.
     *
     * @return dob
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the driver.
     *
     * @param dob the new date of birth
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Gets the age of the driver.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the driver.
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the number of laps completed by the driver.
     *
     * @return laps
     */
    public int getLaps() {
        return laps;
    }

    /**
     * Sets the number of laps completed by the driver.
     *
     * @param laps the new number of laps
     */
    public void setLaps(int laps) {
        this.laps = laps;
    }

    /**
     * Gets the number of races participated in by the driver.
     *
     * @return races
     */
    public int getRaces() {
        return races;
    }

    /**
     * Sets the number of races participated in by the driver.
     *
     * @param races the new number of races
     */
    public void setRaces(int races) {
        this.races = races;
    }

    /**
     * Gets the number of podium finishes by the driver.
     *
     * @return podiums
     */
    public int getPodiums() {
        return podiums;
    }

    /**
     * Sets the number of podium finishes by the driver.
     *
     * @param podiums the new number of podium finishes
     */
    public void setPodiums(int podiums) {
        this.podiums = podiums;
    }

    /**
     * Gets the number of wins achieved by the driver.
     *
     * @return wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the number of wins achieved by the driver.
     *
     * @param wins the new number of wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gets the team ID associated with the driver.
     *
     * @return team
     */
    public int getTeam() {
        return team;
    }

    /**
     * Sets the team ID associated with the driver.
     *
     * @param team the new team ID
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * Gets the kart ID used by the driver.
     *
     * @return kart
     */
    public int getKart() {
        return kart;
    }

    /**
     * Sets the kart ID used by the driver.
     *
     * @param kart the new kart ID
     */
    public void setKart(int kart) {
        this.kart = kart;
    }

    /**
     * Gets the image of the driver.
     *
     * @return img
     */
    public Blob getImg() {
        return img;
    }

    /**
     * Sets the image of the driver.
     *
     * @param img the new image
     */
    public void setImg(Blob img) {
        this.img = img;
    }
}
