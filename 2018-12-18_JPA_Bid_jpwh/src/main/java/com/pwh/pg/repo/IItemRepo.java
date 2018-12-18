package com.pwh.pg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwh.pg.entity.Item;

public interface IItemRepo extends JpaRepository<Item, Long> {

}
