package com.sonny.store.game;

import com.sonny.store.category.CategoryRepository;
import com.sonny.store.common.PageResponse;
import com.sonny.store.platform.Console;
import com.sonny.store.platform.Platform;
import com.sonny.store.platform.PlatformRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;
    private final CategoryRepository categoryRepository;

    public String saveGame(final GameRequest gameRequest) {
        if (gameRepository.existsByTitle(gameRequest.title())) {
            log.info("Game already exists: {}", gameRequest.title());
            // todo: create a dedicated exception
            throw new RuntimeException("Game already exists");
        }

        final List<Console> selectedConsoles = gameRequest.platforms()
                .stream()
                .map(Console::valueOf)
                .toList();

        final List<Platform> platforms = platformRepository.findAllByConsoleIn(selectedConsoles);

        if (platforms.size() != selectedConsoles.size()) {
            log.warn("Received a non supported platform. Received: {} - Stored: {}", selectedConsoles, platforms);
            // todo: create a dedicated exception
            throw new RuntimeException("One or more platforms are not supported");
        }

        if (!categoryRepository.existsById(gameRequest.categoryId())) {
            log.warn("Received a category that does not exist: {}", gameRequest.categoryId());
            // todo: create a dedicated exception
            throw new RuntimeException("Category does not exist");
        }

        final Game game = gameMapper.toGame(gameRequest);
        game.setPlatforms(platforms);
        final Game savedGame = gameRepository.save(game);

        // todo: do we need to assign the game to the selectedPlatforms ?
        return savedGame.getId();
    }

    public void updateGame(String gameId, GameRequest gameRequest) {

    }

    public String uploadGameImage(MultipartFile file, String gameId) {
        return null;
    }

    // the result should be paginated
    public PageResponse<GameResponse> findAllGames(int page, int size) {
        return null;
    }

    public void deleteGame(String gameId) {

    }

}
