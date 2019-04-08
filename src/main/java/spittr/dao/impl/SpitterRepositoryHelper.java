package spittr.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import spittr.dao.SpitterSweeper;

//Jpa 组合查询
public class SpitterRepositoryHelper implements SpitterSweeper{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public int eliteSweep() {
		String update = "update Spitter s"
				+ "set s.status = 'Elite' "
				+ "where s.status = 'Newbie'"
				+ "and s.id in("
				+ "select id from Spitter spitter where ("
				+ " select count(spittles) from spitter.spittles) > 10000 )";
		return em.createQuery(update).executeUpdate();
	}
	
}
