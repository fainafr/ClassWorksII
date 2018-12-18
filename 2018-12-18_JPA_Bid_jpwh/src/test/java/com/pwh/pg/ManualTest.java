package com.pwh.pg;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pwh.pg.entity.Bid;
import com.pwh.pg.entity.Item;
import com.pwh.pg.entity.User;
import com.pwh.pg.repo.IBidRepo;
import com.pwh.pg.repo.IItemRepo;
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
	IUserRepo userRepo;

	@Autowired
	IBidRepo bidRepo;

	@Test
	public void saveTest() {

		itemRepo.save(strat);

		userRepo.save(jimmy);

		bidRepo.save(firstBid);

		bidRepo.save(secondBid);

		userRepo.save(jimmy);

		userRepo.save(jimmy);


	}
	
	@Test 
	public void readTest() {
				
		System.out.println("Item 1" + itemRepo.findById(1l));
		
		System.out.println("Item 1" + itemRepo.findById(1l));

		System.out.println("User 1" + userRepo.findById(1l));

		System.out.println("Bid 1" + bidRepo.findById(1l));

		System.out.println("Bid 2" + bidRepo.findById(2l));
		
	}
}
