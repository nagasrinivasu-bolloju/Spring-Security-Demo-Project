package com.naga.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naga.demo.model.LoginRecords;
import com.naga.demo.repo.UserLoginRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserLoginRepo userRepo;
	
	//THE MAIN AIM OF THIS METHOD IS TO FETCH A SET OF RECORDS FROM THE PROVIDED
	//BEANS(LIKE IN OUR CASE InMemoryUserDetailsManager userDetailsService(){} , 
	//UserDetailsManager users() {} ETC..) AND THEN RETURNING BACK THE 
	//MATCHED RECORD WITH THE GIVEN USERNAME TO THE AUTHENDICATIONPROVIDER. AND THE AUTHENDICATION WILL BE 
	//PERFORMED THERE(AT AUTHENDICATIONPROVIDER ) BASED ON THE RECORD RETURNED BY THIS METHOD.
	//LIKE WISE WE ARE OVVERRIDING THIS METHOD AND DOING THE SAME MANUALLY...
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("UserName is:"+username);
		LoginRecords user=userRepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Username Not Found");
		return new MyUserDetails(user);
	}

}
