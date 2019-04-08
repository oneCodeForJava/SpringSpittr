package spittr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;
import spittr.entity.Spittle;
import spittr.service.SpitterService;

@Service
public class SpitterServiceImpl implements SpitterService {

	@Autowired
	private SpitterRepository spitterRepository;
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		return null;
	}

	@Override
	public Spittle findOne(long spittleId) {
		return null;
	}

	@Override
	@Transactional
	public void save(Spitter spitter) {
		spitterRepository.save(spitter);

	}

	@Override
	public Spitter findByUsername(String username) {
		return spitterRepository.findByUsername(username);
	}

}
