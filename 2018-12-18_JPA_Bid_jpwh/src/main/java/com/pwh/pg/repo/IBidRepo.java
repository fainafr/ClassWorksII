package com.pwh.pg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwh.pg.entity.Bid;
import com.pwh.pg.entity.Item;

public interface IBidRepo extends JpaRepository<Bid, Long> {
	
	public List<Bid> findBidsByItem(Item item);
	
}
