package com.shubham.springsecurityuseraccess.service.auth;


import com.shubham.springsecurityuseraccess.dao.UserDetailsRepository;
import com.shubham.springsecurityuseraccess.dto.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDao;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info(
				"############ Inside CustomUserDetailsService#loadUserByUsername method ########");

		log.info("Username {}", username);

		UserLogin user = userDao.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

		return userBuilder.username(user.getUsername())
				.password(user.getPassword()).roles(user.getRole()).build();
	}
}
