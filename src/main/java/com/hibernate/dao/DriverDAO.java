package com.hibernate.dao;

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
}
