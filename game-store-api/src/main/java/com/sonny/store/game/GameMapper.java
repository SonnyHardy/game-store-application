package com.sonny.store.game;

import com.sonny.store.category.Category;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GameMapper {

    public Game toGame(GameRequest gameRequest) {
        return Game.builder()
                .title(gameRequest.title())
                .category(Category.builder()
                        .id(gameRequest.categoryId())
                        .build())
                //.platforms()
                .build();
    }

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .title(game.getTitle())
                // todo: Set the CDN url
                .imageUrl("Fix-me")
                .platforms(
                        game.getPlatforms()
                        .stream()
                                .map(platform -> platform.getConsole().name())
                                .collect(Collectors.toSet())
                )
                .build();

    }
}
