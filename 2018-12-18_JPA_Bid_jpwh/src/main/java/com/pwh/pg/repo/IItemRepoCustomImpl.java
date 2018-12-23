package com.pwh.pg.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.pwh.pg.entity.Item;

public class IItemRepoCustomImpl implements IItemRepoCustom {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void refresh(Item item) {
		
		em.refresh(item);
		
	}

	public void printEM() {
		System.out.println(em.toString());
		
	}

}
