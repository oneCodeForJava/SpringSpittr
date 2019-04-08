package spittr.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;
import spittr.entity.Spittle;

//@Repository
public class JpaSpitterRepository implements SpitterRepository{

	public JpaSpitterRepository(){
		System.out.println("初始化jpa: " + new Date());
	}
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle findOne(long spittleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Spitter spitter) {
		em.persist(spitter);
		em.flush();
	}

	@Override
	public Spitter findByUsername(String username) {
		Query query = em.createNativeQuery("select * from spitter where username = ? ");
		query.setParameter(1, username);
		Object[] objs = (Object[]) query.getSingleResult();
		Spitter result = new Spitter(Long.valueOf(objs[0].toString()), objs[1].toString(), objs[2].toString(), objs[5].toString(), objs[6].toString());
		em.close();
		return result;
	}

}
