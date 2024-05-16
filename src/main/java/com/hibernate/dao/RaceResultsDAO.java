package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;
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
	
	public static List<RaceResults> selectAllRaceResultsByRace(int race) {
	    Transaction transaction = null;
	    List<RaceResults> raceResultsList = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        Query<RaceResults> query = session.createQuery("FROM RaceResults WHERE race = :race", RaceResults.class);
	        query.setParameter("race", race);
	        raceResultsList = query.getResultList();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	    }
	    return raceResultsList;
	}
	
	public static List<RaceResults> selectAllRaceResultsByDriver(int driver) {
	    Transaction transaction = null;
	    List<RaceResults> raceResultsList = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        Query<RaceResults> query = session.createQuery("FROM RaceResults WHERE driver = :driver", RaceResults.class);
	        query.setParameter("driver", driver);
	        raceResultsList = query.getResultList();
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
	        if (e.getConstraintName().contains("PRIMARY")) {
	            throw new Exception("Primary keys already exist in the database", e);
	        } else if (e.getConstraintName().contains("position")) {
	            throw new Exception("The position is already occupied by another driver", e);
	        } else {
	            throw new Exception("Error inserting race result", e);
	        }
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
	        NativeQuery<RaceResults> query = session.createNativeQuery("DELETE FROM race_results WHERE race = :race AND driver = :driver", RaceResults.class);
	        query.setParameter("race", race);
	        query.setParameter("driver", driver);
	        int deletedCount = query.executeUpdate();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	    }
	}
}
