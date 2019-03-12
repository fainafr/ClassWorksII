package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.ManagedTeam;

public interface ITeamRepo extends JpaRepository<ManagedTeam, String>{

}