package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Kart;
import com.hibernate.util.HibernateUtil;

public class KartDAO {
	
	public static Kart selectKart(int id) {
		Transaction transaction = null;
		Kart kart = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kart = session.get(Kart.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return kart;
	}
	
	public static List<Kart> selectAllKarts() {
		Transaction transaction = null;
		List<Kart> kartList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kartList = session.createQuery("FROM Kart", Kart.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return kartList;
	}
	
	public static List<Kart> selectAvailableKarts() {
		Transaction transaction = null;
		List<Kart> kartList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kartList = session.createQuery("FROM Kart WHERE available = true", Kart.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return kartList;
	}
	
	public static List<Kart> selectUnavailableKarts() {
		Transaction transaction = null;
		List<Kart> kartList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kartList = session.createQuery("FROM Kart WHERE available = false", Kart.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return kartList;
	}
	
	public static void insertKart(Kart kart) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(kart);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void updateKart(Kart kart, boolean available) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kart.setAvailable(available);
			session.merge(kart);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteKart(int id) {
		Transaction transaction = null;
		Kart kart = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			kart = session.get(Kart.class, id);
			session.remove(kart);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
