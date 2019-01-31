package application.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.entities.Person;
import application.entities.Point;

@RestController
@Validated
public class ValidationController {
	
	@PostMapping("/body")
	public String checkBody(@Valid @RequestBody Person person ) {
		return person.toString();
	}
	
	@PostMapping("/parameters")
	public String checkParameters(@RequestParam(name="x") 
	                              @Max(value=5, message="Point: x is too big") 	
	                              @Min(value=-5, message="Point: x is too little") int x, 
	                              @RequestParam(name="y") 
	                              @Max(value=5, message="Point: y is too big")
	                              @Min(value=-5, message="Point: y is too little") int y ) 
	{
		return new Point(x,y).toString();
	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleBodyException(MethodArgumentNotValidException exception) {
 
		return exception.getBindingResult().getFieldErrors().stream()
															.map((e)-> e.getDefaultMessage()+": "+e.getRejectedValue())
															.collect(Collectors.toList());
		
    }
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> handleParamException(ConstraintViolationException exception) {
		
		return exception.getConstraintViolations().stream()
				.map((e)-> e.getMessage()+": "+e.getInvalidValue())
				.collect(Collectors.toList());   
	}

}
