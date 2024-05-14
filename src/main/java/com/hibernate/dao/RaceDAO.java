package com.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Race;
import com.hibernate.util.HibernateUtil;

public class RaceDAO {
	
	public static Race selectRace(int id) {
		Transaction transaction = null;
		Race race = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			race = session.get(Race.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return race;
	}
	
	public static List<Race> selectAllRaces() {
		Transaction transaction = null;
		List<Race> raceList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			raceList = session.createQuery("FROM Race", Race.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return raceList;
	}
	
	public static void updateRace(Race race, LocalDate date, int laps) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			race.setDate(date);
			race.setLaps(laps);
			session.merge(race);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteRace(int id) {
		Transaction transaction = null;
		Race race = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			race = session.get(Race.class, id);
			session.remove(race);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
