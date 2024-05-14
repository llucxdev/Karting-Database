package com.hibernate.dao;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

	public static Driver selectDriver(String name) {
		Transaction transaction = null;
		Driver driver = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Driver> query = session.createQuery("FROM Driver WHERE name = :name", Driver.class);
			query.setParameter("name", name);
			driver = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driver;
	}
	
	public static Driver selectDriverByKart(int kart) {
		Transaction transaction = null;
		Driver driver = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Driver> query = session.createQuery("FROM Driver WHERE kart = :kart", Driver.class);
			query.setParameter("kart", kart);
			driver = query.uniqueResult();
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
	
	public static List<Driver> selectDriversWithoutTeam() {
		Transaction transaction = null;
		List<Driver> driverList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driverList = session.createQuery("FROM Driver WHERE team = 0", Driver.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driverList;
	}
	
	public static List<Driver> selectDriversWithTeam() {
		Transaction transaction = null;
		List<Driver> driverList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driverList = session.createQuery("FROM Driver WHERE team != 0", Driver.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driverList;
	}
	
	public static List<Driver> selectDriversWithoutKart() {
		Transaction transaction = null;
		List<Driver> driverList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driverList = session.createQuery("FROM Driver WHERE kart = 0", Driver.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return driverList;
	}
	
	public static List<Driver> selectDriversWithKart() {
		Transaction transaction = null;
		List<Driver> driverList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driverList = session.createQuery("FROM Driver WHERE kart != 0", Driver.class).getResultList();
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

	public static void updateDriver(Driver driver, String name, LocalDate dob, int age, int laps, int races,
			int podiums, int wins, int team, int kart, Blob img) {
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
			driver.setImg(img);
			session.merge(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void updateDriverTeam(Driver driver, int team) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driver.setTeam(team);
			session.merge(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void updateDriverKart(Driver driver, int kart) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			driver.setKart(kart);
			session.merge(driver);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void updateDriverLap(Driver driver) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int currentLaps = driver.getLaps();
			currentLaps++;
			driver.setLaps(currentLaps);
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
