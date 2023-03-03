package com.naga.demo.security;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.naga.demo.filters.JwtRequestFilter;


@Configuration
public class CustomConfigurationClass{
//    @SuppressWarnings("deprecation")
//    @Bean
//    InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("naga")
//                .password("naga")
//                .roles("USER")
//                .build();
//        UserDetails secondUser = User.withDefaultPasswordEncoder()
//                .username("vasu")
//                .password("vasu")
//                .roles("ADMIN")
//                .build();
//        List<UserDetails> users = Arrays.asList(user, secondUser);
//        return new InMemoryUserDetailsManager(users);
//    }
	
	
	
	
//	JDBC AUNTHENDICATION USING H2 DATABASE AND DEFAULT USER-AND-AUTHORITIES SCHEMAS.
//	DATASOURCE BEAN FOR SPECIFYING THE DEFAULT SCHEMA CREATION STATEMENT
//	@Bean
//    DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.H2)
//            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//            .build();
//    }
//	USERDETAILS BEAN FOR RETRIEVING THE USERS DATA STORED IN DB UPON CALLING 
//	BY THE AUTHENDICATION PROVIDER'S AUTHENDICATE() METHOD.
	
//	@SuppressWarnings("deprecation")
//	@Bean
//    UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//        
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(user2);
//        return users;
//    }
	
//	JDBC AUNTHENDICATION USING H2 DATABASE AND CUSTOM USER-AND-AUTHORITIES SCHEMAS.(Also include two sql files(schema.sql,data.sql)
//	
//	@Bean
//	DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//	            .setType(EmbeddedDatabaseType.H2)
//	            .build();
//	}
//	@Bean
//	UserDetailsManager users(DataSource dataSource) {
//	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//	    
//	    //IF SCHEMAS ARE CREATED WITH SOME OTHER NAMES LIKE my_user,my_table etc..(INSTEAD OF USERS & AUTHORITIES)
//	    //THEN WE HAVE TO SPECIFY THEM TO SPRING SECURITY THROUGH THESE QUERIES BELOW
//	    //OTHERWISE IT CANT FIND THEM.
//	    //users.setUsersByUsernameQuery(
//	    //		"select username,password,'true' as enabled from users where username = ?"
//	    //		);
//	    //users.setAuthoritiesByUsernameQuery(
//	    //		"select username,authority from authorities where username = ?"
//	    //		);
//	    return users;
//	    }
	
//	@Bean
//	MyUserDetailsService myUserDetailsService() {
//		return new MyUserDetailsService();
//	}
	
	//Default password encoder(NoOpPasswordEncoder) which actually didn't do any encoding.
	@SuppressWarnings("deprecation")
	@Bean
	PasswordEncoder getPasswordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
//    	http.authorizeHttpRequests()
//    		.requestMatchers("/calculate").hasRole("ADMIN")
//    		.requestMatchers("/").hasAnyRole("USER","ADMIN")
//    		.requestMatchers("/**").permitAll()
//    		.and().formLogin();
//        return http.build();
    	http.csrf().disable()
    		.authorizeHttpRequests()
    		.requestMatchers("/submit-credentials","/authendicate").permitAll()
    		.anyRequest().authenticated()
    		.and()
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Bean
    DaoAuthenticationProvider daoAuthProvider()
    {
    	DaoAuthenticationProvider daoAuth=new DaoAuthenticationProvider();
    	daoAuth.setUserDetailsService(myUserDetailsService);
    	daoAuth.setPasswordEncoder(getPasswordEncoder());
    	return daoAuth;
    }
}
