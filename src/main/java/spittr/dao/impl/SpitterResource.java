package spittr.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;
import spittr.entity.Spittle;

//@Repository
public class SpitterResource implements SpitterRepository{

/*	@Autowired
	private JdbcOperations jdbcOperations;*/
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return null;
	}
	
	@Override
	public Spittle findOne(long spittleId) {
		return null;
	}
	

	@Override
	public void save(Spitter spitter) {
		sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		session.save(spitter);
	}
	
	@Override
	public Spitter findByUsername(String username) {
		return (Spitter) sessionFactory.getCurrentSession().createCriteria(Spitter.class)
				.add(Restrictions.eq("username", username)).list().get(0);
	}
	
	/*@Override
	public List<Spittle> findSpittles(long max, int count) {
		return null;
	}

	@Override
	public Spittle findOne(long spittleId) {
		return null;
	}

	public static final String SQL_INSERT_SPITTER = "insert into spitter(id, username, password, enabled, role, firstname, lastname)"
			+ "values(SEQ_SPITTER.NEXTVAL,?,?,?,?,?,?)";
	@Override
	public void save(Spitter spitter) {
		jdbcOperations.update(SQL_INSERT_SPITTER, 
				spitter.getUsername(), spitter.getPassword(),
				1, "ROLE",
				spitter.getFirstName(), spitter.getLastName());
	}

	public static final String SQL_QUERY_USERNAME = "select * from spitter where username = ?";
	@Override
	public Spitter findByUsername(String username) {
		return jdbcOperations.queryForObject(SQL_QUERY_USERNAME, new RowMapper<Spitter>(){
			@Override
			public Spitter mapRow(ResultSet rs, int num) throws SQLException {
				return new Spitter(
						rs.getLong("id"), 
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("firstname"),
						rs.getString("lastname"));
			}
		}, username);
	}*/

}
