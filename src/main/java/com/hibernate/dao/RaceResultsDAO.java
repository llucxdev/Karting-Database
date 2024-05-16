package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

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
	
	public static void insertRaceResult(RaceResults raceResults) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(raceResults);
			transaction.commit();
		} catch (ConstraintViolationException e) {
	        throw new Exception("Duplicated entry", e);
	    } catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteRaceResult(int race, int driver) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
	        Query<RaceResults> query = session.createQuery("DELETE FROM RaceResults WHERE race = :race AND driver = :driver", RaceResults.class);
	        query.setParameter("race", race);
	        query.setParameter("driver", driver);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
