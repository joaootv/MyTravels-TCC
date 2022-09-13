package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.User;
import br.com.mytravels.apimytravels.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TransportadoraServices transportadoraServices;
	
	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		
	}
	
	public List<User> findByTransportadora(Long id) {
		Optional<Transportadora> transportadora = this.transportadoraServices.findById(id);
		return repository.findByTransportadora(transportadora);
	}
		
}