package model;

import java.net.URI;

import java.net.URISyntaxException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import dto.Author;
import dto.Countries;
import dto.Publisher;

public class Model {
	
	public static final ParameterizedTypeReference<Iterable<Publisher>> iterablePublisherType =
							new ParameterizedTypeReference<Iterable<Publisher>>() {};
							
	public static final String URL = "http://localhost:8080";
	static RestTemplate restTemplate = new RestTemplate();
	
	public static int findOrAddCountry(Countries country) throws URISyntaxException {
		String urn = "/general/addCountry?country="+country.name();
		
		RequestEntity request = new RequestEntity(null, HttpMethod.POST, new URI(URL+urn));
		ResponseEntity<Integer> response = restTemplate.exchange(request, Integer.class);
		
		return response.getBody();
	}
	
	public static int findOrAddAuthor(Author author) throws URISyntaxException {
		String urn = "/general/addAuthor?first_name="+author.getFirstName()+"&last_name="+author.getLastName();
		
		RequestEntity request = new RequestEntity(null, HttpMethod.POST, new URI(URL+urn));
		ResponseEntity<Integer> response = restTemplate.exchange(request, Integer.class);
		
		return response.getBody();
	}
	
	public static int findOrAddPublisher(Publisher publisher) throws URISyntaxException {
		String urn = "/general/addPublisher?publisher_name="+
							publisher.getName().replaceAll(" ", "_")+"&country="+publisher.getCountry().name();
		
		RequestEntity request = new RequestEntity(null, HttpMethod.POST, new URI(URL+urn));
		ResponseEntity<Integer> response = restTemplate.exchange(request, Integer.class);
		
		return response.getBody();
	}
	
	public static Publisher getPublisherById(int id) throws URISyntaxException {
		
		String urn = "/query/getPublisherById?id="+id;
		RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(URL+urn));
		ResponseEntity<Publisher> response = restTemplate.exchange(request, Publisher.class);
		
		return response.getBody();
	}
	
	public static Iterable<Publisher> getPublishersByCountry(Countries country) throws URISyntaxException {
		
		String urn = "/query/getPublishersByCountry?country="+country.name();
		RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(URL+urn));
		ResponseEntity<Iterable<Publisher>> response = restTemplate.exchange(request, iterablePublisherType);
		
		return response.getBody();
	}
	
	

}
