package model;

import org.springframework.stereotype.Service;

@Service
public class ApplicationAPI implements IApplication{

	@Override
	public String everybody() {return "everybody";}
	
	@Override
	public String guestAndMore() {return "guest and more";}
	
	@Override
	public String managerAndMore() {return "manager and more";}
	
	@Override
	public String admin() {return "admin";}
	
}
