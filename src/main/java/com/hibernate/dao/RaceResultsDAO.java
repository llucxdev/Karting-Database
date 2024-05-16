package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.RaceResults;
import com.hibernate.util.HibernateUtil;

public class RaceResultsDAO {
	
	public static RaceResults selectRaceResult(int id) {
		Transaction transaction = null;
		RaceResults raceResults = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			raceResults = session.get(RaceResults.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return raceResults;
	}
	
	public static List<RaceResults> selectAllRaceResults() {
		Transaction transaction = null;
		List<RaceResults> raceResultsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			raceResultsList = session.createQuery("FROM RaceResults", RaceResults.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return raceResultsList;
	}
	
	public static void insertRaceResult(RaceResults raceResults) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(raceResults);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteRaceResult(int id) {
		Transaction transaction = null;
		RaceResults raceResults = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			raceResults = session.get(RaceResults.class, id);
			session.remove(raceResults);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
