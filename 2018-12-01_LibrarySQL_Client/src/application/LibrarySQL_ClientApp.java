package application;

import java.net.URISyntaxException;

import dto.Author;
import dto.Countries;
import dto.Publisher;
import model.Model;

public class LibrarySQL_ClientApp {

	public static void main(String[] args) throws URISyntaxException {
		
		//System.out.println(Model.addCountry(Countries.Germany));
/*		
		Author author = Author.getRandomAuthor();
		System.out.println(author);
		System.out.println(Model.addAuthor(author));
*/
/*
		for (int i=0; i<30; i++) {
			Publisher publisher = Publisher.getRandomPublisher();
			System.out.println(publisher);
			System.out.println(Model.findOrAddPublisher(publisher));
		}
*/
		//System.out.println(Model.getPublisherById(22));
		
		for (Publisher publisher : Model.getPublishersByCountry(Countries.Israel))System.out.println(publisher);
	}

}
