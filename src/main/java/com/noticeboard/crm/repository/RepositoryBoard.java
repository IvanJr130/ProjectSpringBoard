package com.noticeboard.crm.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noticeboard.crm.model.Board;

@Repository
public interface RepositoryBoard extends JpaRepository<Board,Integer>{
	
}
