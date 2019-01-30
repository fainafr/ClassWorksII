package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.IApplication;

@RestController
@RequestMapping(value=API_const.USER_CONTROLLER)
public class ApplicationController {
	
	@Autowired
	IApplication model;
	
	@GetMapping(value = API_const.EVERYBODY)
	public String everybody() {return model.everybody();}
	
	@GetMapping(value = API_const.GUEST)
	public String guest() {return model.guestAndMore();}
	
	@GetMapping(value = API_const.MANAGER)
	public String manager() {return model.managerAndMore();}
	
	@GetMapping(value = API_const.ADMIN)
	public String admin() {return model.admin();}

}
