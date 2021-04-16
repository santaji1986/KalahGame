package com.backbase.kalah.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backbase.kalah.dto.GameDTO;
import com.backbase.kalah.dto.NewGameDTO;
import com.backbase.kalah.exception.GameNotFoundException;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.repository.GameRepository;

@Service
public class GameService implements IGameService {
	GameRepository gameRepository;
	private IGamePlay gamePlay;

	public GameService(GameRepository gameRepository,IGamePlay gamePlay) {
		this.gameRepository = gameRepository;
		this.gamePlay = gamePlay;
	}

	@Override
	public NewGameDTO createNewGame(String url) {
		Game game = gameRepository.save(new Game());
		return new NewGameDTO(game.getId(), getGameURL(url, game));
	}

	private String getGameURL(String url, Game game) {
		return url + "/games/" + game.getId();
	}

	@Override
	public GameDTO makeMove(Long gameId, int pitId, String url) {
		Game afterMoveGame = null;
		Optional<Game> optionalGame = gameRepository.findById(gameId);
		if (optionalGame.isPresent()) {
			Game game = optionalGame.get();
			afterMoveGame = gamePlay.makeMove(game, pitId);
			gameRepository.save(afterMoveGame);
		} else
			throw new GameNotFoundException("GameId does not exists for Id " + gameId);
		return new GameDTO(afterMoveGame.getId(), getGameURL(url, afterMoveGame), afterMoveGame.getBoard());
	}

	

	

}
