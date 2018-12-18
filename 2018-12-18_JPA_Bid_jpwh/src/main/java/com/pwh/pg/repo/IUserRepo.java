package com.pwh.pg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwh.pg.entity.User;

public interface IUserRepo extends JpaRepository<User, Long> {

}
