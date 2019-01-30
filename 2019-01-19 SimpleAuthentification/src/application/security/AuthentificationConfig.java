package application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import application.controllers.API_const;


@Configuration
public class AuthentificationConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http)throws Exception{ //HTTPSec is autocreated? 
		
		/*
		 * Invocations. Without 1st no POST request; 
		 */
		http.csrf().disable();//allows for spring security 2 running POST requests 
		http.httpBasic();//enabling web security 
		
		//only ADMIN may perform the following requests
		http.authorizeRequests().antMatchers(API_const.ADMIN_CONTROLLER+API_const.ADD_ACCOUNT,
											API_const.ADMIN_CONTROLLER+API_const.ADD_ACCOUNT,
											API_const.ADMIN_CONTROLLER+API_const.ADD_ROLE,
											API_const.ADMIN_CONTROLLER+API_const.GET_PASSWORD,
											API_const.ADMIN_CONTROLLER+API_const.GET_ROLES,
											API_const.USER_CONTROLLER+API_const.ADMIN
		).hasRole("ADMIN");
		
		
		//only MANAGER and ADMIN may perform the following requests
		http.authorizeRequests().antMatchers(API_const.USER_CONTROLLER+API_const.MANAGER).hasAnyRole("ADMIN","MANAGER");
		
		//any user, having role may perform the following requests
		http.authorizeRequests().antMatchers(API_const.USER_CONTROLLER+API_const.GUEST).authenticated();
		
		//any non-authorized user may perform the following methods
		http.authorizeRequests().antMatchers(API_const.USER_CONTROLLER+API_const.EVERYBODY).permitAll();
	}
}
