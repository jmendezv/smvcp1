package pep.mendez.smvcp1.spring.model.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pep.mendez.smvcp1.spring.model.entities.AuthorityEntity;
import pep.mendez.smvcp1.spring.model.entities.UserEntity;
import pep.mendez.smvcp1.spring.model.repository.UserRepository;

/**
 * Core interface which loads user-specific data.
 * 
 * TODO throw a org.springframework.security.SpringSecurityException to set
 * SPRING_SECURITY_LAST_EXCEPTION
 * 
 * @author pep
 *
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);
		String password = user.getPassword();
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (AuthorityEntity authority : user.getAuthorities()) {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(
					authority.getAuthority());
			authorities.add(sga);
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				username, password, authorities);
		return userDetails;
	}

}
