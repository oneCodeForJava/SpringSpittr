package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import spittr.Spitter;
import spittr.Spittle;

@Component
public class SpittleResource implements SpittleRepository{

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		List<Spittle> results = new ArrayList<Spittle>();
		for(int i = 0; i < count; i++){
			Spittle spittle = new Spittle("haha"+i, new Date(), i * 0.1, i * 0.2);
			results.add(spittle);
		}
		return results;
	}

	@Override
	public Spittle findOne(long spittleId) {
		return new Spittle("spittle", new Date(), 0.3, 0.4);
	}

	@Override
	public Spitter save(Spitter spitter) {
		spitter.setId(24L);
		return spitter;
	}

	@Override
	public Spitter findByUsername(String username) {
		return new Spitter(24L, username, "24Hours", "Jack", "Bauer");
	}

}
