package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.hibernate.exception.RaceResultException;
import com.hibernate.exception.RaceResultException.PrimaryKeyViolationException;
import com.hibernate.exception.RaceResultException.PositionOccupiedException;
import com.hibernate.exception.RaceResultException.RaceResultInsertionException;
import com.hibernate.model.RaceResult;
import com.hibernate.util.HibernateUtil;

public class RaceResultDAO {

    public static RaceResult selectRaceResult(int raceId, int driverId) {
        Transaction transaction = null;
        RaceResult raceResults = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<RaceResult> query = session.createQuery("FROM RaceResult WHERE race = :race AND driver = :driver", RaceResult.class);
            query.setParameter("race", raceId);
            query.setParameter("driver", driverId);
            raceResults = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return raceResults;
    }

    public static RaceResult selectRaceResultByPosition(int raceId, int position) {
        Transaction transaction = null;
        RaceResult raceResults = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<RaceResult> query = session.createQuery("FROM RaceResult WHERE race = :race AND position = :position", RaceResult.class);
            query.setParameter("race", raceId);
            query.setParameter("position", position);
            raceResults = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return raceResults;
    }

    public static List<RaceResult> selectAllRaceResults() {
        Transaction transaction = null;
        List<RaceResult> raceResultsList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            raceResultsList = session.createQuery("FROM RaceResult", RaceResult.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return raceResultsList;
    }

    public static List<RaceResult> selectAllRaceResultsByRace(int race) {
        Transaction transaction = null;
        List<RaceResult> raceResultsList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<RaceResult> query = session.createQuery("FROM RaceResult WHERE race = :race", RaceResult.class);
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

    public static List<RaceResult> selectAllRaceResultsByDriver(int driver) {
        Transaction transaction = null;
        List<RaceResult> raceResultsList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<RaceResult> query = session.createQuery("FROM RaceResult WHERE driver = :driver", RaceResult.class);
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

    public static void insertRaceResult(RaceResult raceResults) throws PrimaryKeyViolationException, PositionOccupiedException, RaceResultInsertionException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(raceResults);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e.getConstraintName().contains("PRIMARY")) {
                throw new PrimaryKeyViolationException("Primary keys already exist in the database", e);
            } else if (e.getConstraintName().contains("position")) {
                throw new PositionOccupiedException("The position is already occupied by another driver", e);
            } else {
                throw new RaceResultInsertionException("Error inserting race result", e);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RaceResultInsertionException("Error inserting race result", e);
        }
    }

    public static void deleteRaceResult(int raceId, int driverId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery<RaceResult> query = session.createNativeQuery("DELETE FROM race_result WHERE race = :race AND driver = :driver", RaceResult.class);
            query.setParameter("race", raceId);
            query.setParameter("driver", driverId);
            int deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
