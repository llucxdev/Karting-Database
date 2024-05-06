package com.hibernate.dao;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
	
	public static void updateteam(Team team, String name, LocalDate date, Blob img) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			team.setName(name);
			team.setDate(date);
			team.setImg(img);
			session.merge(team);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public static void deleteteam(int id) {
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
