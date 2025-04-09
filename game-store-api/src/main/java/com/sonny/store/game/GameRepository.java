package com.sonny.store.game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {

    boolean existsByTitle(String title);
}
