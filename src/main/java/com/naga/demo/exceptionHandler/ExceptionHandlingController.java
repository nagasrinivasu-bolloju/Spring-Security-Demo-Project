package com.naga.demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlingController{
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Model m)   {
		m.addAttribute("errorMsg","Sorry Requested Page Not Found!!!");
            return "error";
    }
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	@ExceptionHandler(Forbidden.class)
    public String handleError403(Model m)   {
		m.addAttribute("errorMsg","!!!You Are Not An Authorised Person To Access THis Page!!!");
            return "error";
    }
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String nullPointer(Model m)
	{
		m.addAttribute("errorMsg","Internal Server Error Occured!!");
		return "error";
	}
}
