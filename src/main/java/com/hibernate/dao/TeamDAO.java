package com.hibernate.dao;

import java.sql.Blob;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Driver;
import com.hibernate.model.Team;
import com.hibernate.util.HibernateUtil;

public class TeamDAO {

	public static Team selectTeam(int id) {
		Transaction transaction = null;
		Team team = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			team = session.get(Team.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return team;
	}

	public static Team selectTeam(String name) {
		Transaction transaction = null;
		Team team = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Team> query = session.createQuery("FROM Team WHERE name = :name", Team.class);
			query.setParameter("name", name);
			team = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return team;
	}

	public static List<Team> selectAllTeams() {
		Transaction transaction = null;
		List<Team> teamList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			teamList = session.createQuery("FROM Team", Team.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return teamList;
	}

	public static void insertTeam(Team team) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public static void updateTeam(Team team, String name, Blob img) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			team.setName(name);
			team.setImg(img);
			session.merge(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public static void updateTeamAddDriver(Team team, Driver driver) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			team.addDriver(driver);
			session.merge(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public static void updateTeamRemoveDriver(Team team, int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Driver> driversList = team.getDrivers();
	        Iterator<Driver> iterator = driversList.iterator();
	        while (iterator.hasNext()) {
	            Driver driver = iterator.next();
	            if (driver.getDriver_id() == id) {
	                iterator.remove();
	            }
	        }
			session.merge(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public static void deleteTeam(int id) {
		Transaction transaction = null;
		Team team = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			team = session.get(Team.class, id);
			session.remove(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
