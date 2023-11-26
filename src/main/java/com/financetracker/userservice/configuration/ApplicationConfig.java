package com.financetracker.userservice.configuration;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.financetracker.authflow.JwtService;
import com.financetracker.userservice.modal.User;
import com.financetracker.userservice.repository.UserRepository;

@Configuration
public class ApplicationConfig {

	private UserRepository userRepository;

	@Autowired
	public ApplicationConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
				User user = userRepository.findByUserName(userName);

				if (Objects.isNull(user)) {
					throw new UsernameNotFoundException("No user found with this user name " + userName);
				}

				return new org.springframework.security.core.userdetails.User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getPassword()),
						Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
			}
		};
	}

	@Bean
	public AuthenticationProvider authenticataionProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(getUserDetailsService());
		authProvider.setPasswordEncoder(getPassWordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder getPassWordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public JwtService jwtSerivce() {
		return new JwtService();
	}

}
