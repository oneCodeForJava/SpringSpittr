package spittr.data;

import java.util.List;

import spittr.Spitter;
import spittr.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count); 
	Spittle findOne(long spittleId);
	Spitter save(Spitter spitter);
	Spitter findByUsername(String username);
}
