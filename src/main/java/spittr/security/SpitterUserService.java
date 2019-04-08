package spittr.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;

@Service
public class SpitterUserService implements UserDetailsService {

	@Autowired
	private SpitterRepository spittleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authrities = new ArrayList<>();
		Spitter spitter = spittleRepository.findByUsername(username);
		authrities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
		return new User(spitter.getUsername(), spitter.getPassword(), authrities);
	}

}
