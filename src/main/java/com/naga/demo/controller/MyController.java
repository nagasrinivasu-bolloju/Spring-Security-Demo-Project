package com.naga.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.naga.demo.model.AuthendicationRequest;
import com.naga.demo.model.AuthendicationResponse;
import com.naga.demo.security.MyUserDetailsService;
import com.naga.demo.util.JwtUtil;

@RestController
public class MyController {
	
	@Autowired
	private DaoAuthenticationProvider daoAuth;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping("/submit-credentials")
	public ModelAndView submitCredentials()
	{
		System.out.println("Inside /submit-credentials");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("loginPage");
		return mv;
	}
	
	@RequestMapping(value="/authendicate",method=RequestMethod.POST)
	public ResponseEntity<?> getAuthendicate(@RequestBody AuthendicationRequest authRequest) throws Exception
//	public ResponseEntity<?> getAuthendicate(AuthendicationRequest authRequest) throws Exception
	{
		try
		{
		Authentication authentication=new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());
		Authentication result=daoAuth.authenticate(authentication);
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Incorrect credentials "+e);
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthendicationResponse(jwt));
	}
	@GetMapping("/")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	@GetMapping("/calculate")
	public ModelAndView add(@RequestParam int a,@RequestParam int b,@RequestParam int c,@RequestParam int d)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("display");
		mv.addObject("msg","Hii welcome to the Display Page.");
		double ans=((2*(Math.pow(a, 4)))+(6*(Math.pow(b, 2)))+c)/d;
		System.out.println("ans is:"+ans);
		mv.addObject("ans",ans);
		return mv;
	}
	@RequestMapping("/logout-of-device")
	public String logoutMethod()
	{
		return "redirect:/logout";
	}
	@RequestMapping("/add")
	public ModelAndView add()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("addView");
		return mv;
	}
	//Sample API for postman calls checking..
	@RequestMapping("/get-data")
	public Model getData(Model mv)
	{
		mv.addAttribute("msg","Hi there... I am an API connecting the back end for your calls..");
		return mv;
	}
	
}
