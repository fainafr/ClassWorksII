package com.library;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.library.entity.Event;
import com.library.entity.User;
import com.library.repo.IEventRepo;
import com.library.repo.IUserRepo;

/**
 * Shows that when there is no mappedBy, a join table is done; No cascading;
 */
@Component
public class NoOwnerTestRunner implements CommandLineRunner {

	private final String ANAME = "Alyssa";
	private final String TNAME = "Testing";
	private final User ALYSSA = new User();
	private final Event TESTING = new Event();

	@Autowired
	IUserRepo userRepo;

	@Autowired
	IEventRepo eventRepo;

	/**
	 * Cascading from parent;
	 */
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		ALYSSA.setUserName(ANAME);
		TESTING.setName(TNAME);
		ALYSSA.getEvents().add(TESTING); // -> if I do this without cascading, would not save;

		if (userRepo.findAll().isEmpty()) {
			userRepo.save(ALYSSA);
		}
		User savedA = userRepo.findAll().get(0);

		System.out.println(savedA);

		System.out.println("Is EventRepo empty? " + eventRepo.findAll().isEmpty());

	}
	/*
	 * cascade tells the provider to save the children entities even if the parent
	 * entity doesn't own them, so it effectively modifies the rule. If you had (or
	 * have) mappedBy=child.field and did NOT have cascade then the children of the
	 * list wouldn't be saved. Also, if you didn't have mappedBy AND didn't have
	 * cascade then the Parent owns the relationship and if you put NEW children in
	 * the list and then save the Parent it will *throw an exception* because the new
	 * children ID's aren't available to be saved in the join table. Hope that
	 * clarifies things
	 */

//	/**
//	 * Cascading from child;
//	 */
//	@Override
//	@Transactional
//    public void run(String... args) throws Exception {
//		
//		ALYSSA.setUserName(ANAME);
//		TESTING.setName(TNAME);
//		//TESTING.setUser(ALYSSA); -> if I do this without cascading, would not save; 
//    	
//		if( eventRepo.findAll().isEmpty()) {
//			eventRepo.save(TESTING);
//		} 
//		Event savedE = eventRepo.findAll().get(0); 
//		
//		System.out.println(savedE);
//		
//		System.out.println("Is UserRepo empty? " + userRepo.findAll().isEmpty());
//	
//    }

}