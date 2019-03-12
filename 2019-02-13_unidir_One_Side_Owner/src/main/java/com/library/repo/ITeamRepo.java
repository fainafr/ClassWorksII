package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Team;

public interface ITeamRepo extends JpaRepository<Team, String>{

}