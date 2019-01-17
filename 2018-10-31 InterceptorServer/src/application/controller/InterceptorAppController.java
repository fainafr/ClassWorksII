package application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorAppController {
	
	@GetMapping(value = "/")
	public String getRequest() {
		return "controller";
	}

}
