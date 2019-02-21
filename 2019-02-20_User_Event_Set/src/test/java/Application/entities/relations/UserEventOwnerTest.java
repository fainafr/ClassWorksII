package Application.entities.relations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import Application.entities.EventItem;
import Application.entities.UserItem;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

/**
 * Relation: OneToMany User is the primary entity. Event must have a user as its
 * owner.
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class UserEventOwnerTest {

	private final UserItem ALYSSA = new UserItem();
	private final UserItem BEN = new UserItem();
	private final EventItem TESTING = new EventItem();

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	@Before
	public void buildEntities() {
		ALYSSA.setFirstName("Alyssa");
		BEN.setFirstName("Ben");
	}

	/**
	 * Checking that the UserItem persists his events automatically; Checking that
	 * toString works in the bidirectional relation;
	 */
	@Test
	public void onUserSaveReadEvent() {

		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		assertTrue(em.find(UserItem.class, ALYSSA.getId()) != null);

		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		EventItem savedE = eventRepo.findAll().get(0);

		UserItem savedAfromEvent = savedE.getUserItemOwner();
		assertTrue(savedAfromEvent.equals(ALYSSA));
		System.out.println(savedAfromEvent);

		savedE = savedA.getEventItemsOwner().iterator().next();
		assertTrue(savedE.equals(TESTING));
		System.out.println(savedE);
	}

	/**
	 * Checking that the events are deleted after the UserItem
	 */
	@Test
	public void onUserAndEventSaveDeleteUser() {

		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);
		assertTrue(eventRepo.existsById(TESTING.getId()));

		userRepo.delete(ALYSSA);
		assertFalse(eventRepo.existsById(TESTING.getId()));

	}

	/**
	 * Changing the owner of the event and testing the results; Testing
	 * UserItems.equals();
	 */
	@Test
	public void onUserSaveChangeEvent() {

		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		EventItem savedE = userRepo.findById(ALYSSA.getId()).get().getEventItemsOwner().iterator().next();
		assertTrue(TESTING.equals(savedE));

		System.out.println("Transfer " + ALYSSA.transferEvent(savedE, BEN));
		BEN.addEvent(savedE);

		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		eventRepo.save(TESTING);

		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		UserItem savedB = userRepo.findById(BEN.getId()).get();
		assertFalse(savedA.getEventItemsOwner().contains(TESTING));
		assertTrue(savedB.getEventItemsOwner().contains(TESTING));
		assertTrue(eventRepo.existsById(TESTING.getId()));
		assertEquals(eventRepo.findById(TESTING.getId()).get().getUserItemOwner(), BEN);

	}

	/**
	 * This test demonstrates that UserItems allow us to add EventItems without
	 * checking if two UserItems share the same EventItem;
	 */
	@Test
	public void twoOwnersSameEvent() {

		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		ALYSSA.transferEvent(TESTING, BEN);
		BEN.addEvent(TESTING);

		userRepo.save(ALYSSA);
		userRepo.save(BEN);
		eventRepo.save(TESTING);

		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		UserItem savedB = userRepo.findById(BEN.getId()).get();

		assertTrue(savedA.getEventItemsOwner().size() == 0);
		assertTrue(savedB.getEventItemsOwner().size() == 1);

	}

	@Test
	public void saveEventReadItInUserList() {

		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventItemsOwner().size() == 1);

	}
	

	@Test
	public void saveDuplicateEvent() {

		ALYSSA.addEvent(TESTING);
		ALYSSA.addEvent(TESTING);
		userRepo.save(ALYSSA);
		eventRepo.save(TESTING);

		UserItem savedA = userRepo.findById(ALYSSA.getId()).get();
		assertTrue(savedA.getEventItemsOwner().size() == 1);

	}

}
