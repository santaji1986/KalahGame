package com.backbase.kalah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backbase.kalah.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
