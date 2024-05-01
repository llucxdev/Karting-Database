package com.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Driver;
import com.hibernate.util.HibernateUtil;

public class DriverDAO {

	public static Driver selectDriver(int id) {
		Transaction transaction = null;
		Driver driver = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driver = session.get(Driver.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driver;
	}
	
	public static List<Driver> selectAllDrivers() {
		Transaction transaction = null;
		List<Driver> driverList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driverList = session.createQuery("FROM Driver", Driver.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driverList;
	}
	
	public static void insertDriver(Driver driver) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void updateDriver(Driver driver, String name, LocalDate dob, int age, int laps, int races, int podiums, int wins,
			int team, int kart) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driver.setName(name);
			driver.setDob(dob);
			driver.setAge(age);
			driver.setLaps(laps);
			driver.setRaces(races);
			driver.setPodiums(podiums);
			driver.setWins(wins);
			driver.setTeam(team);
			driver.setKart(kart);
			session.merge(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteDriver(int id) {
		Transaction transaction = null;
		Driver driver = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driver = session.get(Driver.class, id);
			session.remove(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
