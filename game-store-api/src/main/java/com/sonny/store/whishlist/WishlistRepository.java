package com.sonny.store.whishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishlistRepository extends JpaRepository<Wishlist, String> {

    @Query("""
           SELECT COUNT(w)
           FROM Wishlist w
           JOIN w.games g
           WHERE w.id = :gameId
           """)
    long countByGamesId(String gameId);
}
