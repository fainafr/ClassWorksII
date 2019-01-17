package com.pwh.pg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.pwh.pg.entity.Bid;
import com.pwh.pg.entity.Item;
import com.pwh.pg.entity.User;
import com.pwh.pg.repo.IBidRepo;
import com.pwh.pg.repo.IItemRepo;
import com.pwh.pg.repo.IItemRepoCustomImpl;
import com.pwh.pg.repo.IUserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManualTest {

	static final Item strat = new Item(1l, "Stratocaster 1959 mahogany", new HashSet<Bid>());
	static final User jimmy = new User(1l, "Jimmy Hendrix");
	static final Bid firstBid = new Bid(1l, BigDecimal.valueOf(900l), LocalDateTime.now().plus(10, ChronoUnit.DAYS),
			strat, jimmy);
	static final Bid secondBid = new Bid(2l, BigDecimal.valueOf(1000l), LocalDateTime.now().plus(10, ChronoUnit.DAYS),
			strat, jimmy);

	@Autowired
	IItemRepo itemRepo;

	@Autowired
	private IItemRepoCustomImpl itemRepoCustom;

	@Autowired
	IUserRepo userRepo;

	@Autowired
	IBidRepo bidRepo;

	@PersistenceContext // https://www.javabullets.com/access-entitymanager-spring-data-jpa/
	private EntityManager em;

	@Before
	@Transactional
	public void addEric() {

		final User eric = new User(2l, "Eric Clapton");
		em.persist(eric);
		
	}

	// jpwh 10.2.3
	@Test
	@Transactional //https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional/
	public void emPersistFind() {

		User persistentEric = em.find(User.class, 2l);

		User persistentGod = em.find(User.class, 2l);

		assertTrue(persistentGod == persistentEric);
		assertTrue(persistentGod.equals(persistentEric));
		assertTrue(persistentGod.getId().equals(persistentEric.getId()));
	}

	/*
	 * Need to study @Commit more
	 */
	// jpwh 10.2.5; no assertNull(entity); entities in this project have manual ID
	@Test
	@Transactional //https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional/
	@Commit // https://stackoverflow.com/a/19334129
	public void emPersistToTransient() {

		User persistentEric = em.find(User.class, 2l);
		
		assertTrue(em.contains(persistentEric));

		em.remove(persistentEric);
		
		em.flush();

		assertFalse(em.contains(persistentEric));

		em.close();

	}

	@Test
	@Transactional //https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional/
	public void saveTest() {

		itemRepo.save(strat);

		userRepo.save(jimmy);

		bidRepo.save(firstBid);

		bidRepo.save(secondBid);

		userRepo.save(jimmy);

		userRepo.save(jimmy);
		
		/*
		 * Eric was added in @Before
		 */
		User persistentGod = em.find(User.class, 2l);
		
		assertTrue(em.contains(persistentGod));

	}

	@Test
	@Transactional //https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional/
	public void readTest() {

		itemRepoCustom.printEM();

		System.out.println("Item 1" + itemRepo.findById(1l));

		System.out.println("Item 1" + itemRepo.findById(1l));

		System.out.println("User 1" + userRepo.findById(1l));

		System.out.println("Bid 1" + bidRepo.findById(1l));

		System.out.println("Bid 2" + bidRepo.findById(2l));

	}
}
