package com.pwh.pg.repo;

import com.pwh.pg.entity.Item;

//https://www.javabullets.com/access-entitymanager-spring-data-jpa/
public interface IItemRepoCustom {

	void refresh(Item item);

}
