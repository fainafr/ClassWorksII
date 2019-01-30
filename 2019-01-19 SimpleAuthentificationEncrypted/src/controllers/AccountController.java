package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.IAccount;

@RestController
@RequestMapping(value=API_const.ADMIN_CONTROLLER)
public class AccountController {
	
	@Autowired
	IAccount model;
	
	@GetMapping(value = API_const.ADD_ACCOUNT)
	public boolean addAccount(@RequestParam(value="log") String login, @RequestParam(value="psw") String password) {
		return model.addAccount(login, password);
	}
	
	@GetMapping(value = API_const.ADD_ROLE)
	public boolean addRole(@RequestParam(value="log") String login, @RequestParam(value="role") String role) {
		return model.addRole(login, "ROLE_"+role);
	}
	
	@GetMapping(value = API_const.GET_PASSWORD)
	public String getPassword(@RequestParam(value="log") String login) {
		return model.getPassword(login);
	}
	
	@GetMapping(value = API_const.GET_ROLES)
	public String[] getRoles(@RequestParam(value="log") String login) {
		return model.getRoles(login);
	}
	
	@GetMapping(value = "/getAccounts")
	public String[] getAccounts(@RequestParam(value="role") String role) {
		return model.getAccounts(role);
	}

}
