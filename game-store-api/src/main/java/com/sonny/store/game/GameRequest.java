package com.sonny.store.game;


import java.util.List;

public record GameRequest(
        String title,
        String categoryId,
        List<String> platforms
) {
}
