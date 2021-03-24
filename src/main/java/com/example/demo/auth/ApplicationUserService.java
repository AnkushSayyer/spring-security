package com.example.demo.auth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class ApplicationUserService implements UserDetailsService {

	@Autowired private UserRepository userRepository; 
	
	private final ApplicationUserDao applicationUserDao;
	
	@Autowired
	public ApplicationUserService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
		super();
		this.applicationUserDao = applicationUserDao;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return applicationUserDao.selectApplicationUserByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> u = userRepository.findAll();
		User user =  userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
		
		Set<SimpleGrantedAuthority> authorities = Arrays.stream(user.getAuthorities().split(",")).map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toSet());
		return new ApplicationUser(authorities, user.getUsername(), user.getPassword(), true, true, true, true);
	}
}
