package spittr.service;

import java.util.List;

import spittr.entity.Spitter;
import spittr.entity.Spittle;

public interface SpitterService {
	List<Spittle> findSpittles(long max, int count); 
	Spittle findOne(long spittleId);
	

	void save(Spitter spitter);
	Spitter findByUsername(String username);
}
