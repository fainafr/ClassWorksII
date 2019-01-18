package application;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import application.entities.Car;

public class MongoSBcrudAppClient {
	
	static final String URL = "http://localhost:8080";
	static ParameterizedTypeReference<List<Car>> type = 
								new ParameterizedTypeReference<List<Car>>(){};

	public static void main(String[] args) throws URISyntaxException {
	
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		ArrayList<Car> garage = new ArrayList<>();
		
		garage.add(new Car(1,"Zhiguli", 4, 1.3, LocalDate.of(1983, 1, 1), false));
		garage.add(new Car(2,"Zaporozhets", 4, 0.8, LocalDate.of(1973, 12, 31), false));
		garage.add(new Car(3,"Pobeda", 2, 2.2, LocalDate.of(1953, 6, 15), false));
		garage.add(new Car(4,"Toyota", 4, 1.8, LocalDate.of(1993, 3, 8), true));
		garage.add(new Car(5,"Ford", 2, 5.3, LocalDate.of(2018, 12, 15), true));
/*		
		String urn = "/addAll";
		RequestEntity request = new RequestEntity(garage, headers, HttpMethod.POST, new URI(URL+urn));
		restTemplate.exchange(request, Void.class);
*/
		/*
		String urn = "/getAll";
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI(URL+urn));
		ResponseEntity<List<Car>> response = restTemplate.exchange(request, type);
		for (Car c : response.getBody())System.out.println(c);
*/
		String urn = "/changeProduction?id=5";
		RequestEntity request = new RequestEntity(LocalDate.of(2018, 11, 7), headers, HttpMethod.POST, new URI(URL+urn));
		System.out.println(restTemplate.exchange(request, Void.class));
	}

}
