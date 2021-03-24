package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.security.ApplicationUserRole;
import com.google.common.collect.Lists;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDao {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUser()
				.stream()
				.filter(applicationUser -> applicationUser.getUsername().equals(username))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUser(){
		return Lists.newArrayList(
				new ApplicationUser(ApplicationUserRole.STUDENT.getGrantedAuthorities(),
						"annasmith",
						passwordEncoder.encode("password"), 
						true, true, true, true),
				new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),
						"linda",
						passwordEncoder.encode("password"), 
						true, true, true, true),
				new ApplicationUser(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(),
						"tom",
						passwordEncoder.encode("password"), 
						true, true, true, true)
				);
	}


}
