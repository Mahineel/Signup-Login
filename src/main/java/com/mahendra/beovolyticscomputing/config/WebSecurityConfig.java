package com.mahendra.beovolyticscomputing.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.model.PropertyValueProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.mahendra.beovolyticscomputing.services.UserDetailsService;
import com.mahendra.beovolyticscomputing.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public 	AuthenticationProvider authProvider() {
		DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.
    	authorizeRequests().antMatchers("/", "/signup", "/do_login", "/error-page").permitAll().
    	antMatchers("/welcome").hasAnyAuthority("User").anyRequest().authenticated()
		.and()
		.formLogin()
		       .loginPage("/").loginProcessingUrl("/do_login").permitAll()
		       .defaultSuccessUrl("/welcome")
		       .failureUrl("/?error=true").and().csrf().disable(); 
	
        
    }

	
	

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
