package com.backbase.kalah.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.backbase.kalah.GlobalConstants.Player;
import com.backbase.kalah.dto.GameDTO;
import com.backbase.kalah.dto.NewGameDTO;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.repository.GameRepository;

@SpringBootTest
@ContextConfiguration
//@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {
	@Resource
	GameService gameService;
	@Mock
	GameRepository gameRepository;
	@Mock
	IGamePlay gamePlay;

	@Test
	void testCreateNewGame() {
		String url = "http://localhost:8080";
		Long gameId = 1L;
		NewGameDTO expected = new NewGameDTO(gameId, url + "/" + gameId);
		Game game = new Game();
		game.setId(gameId);
		when(gameRepository.save(new Game())).thenReturn(game);
		NewGameDTO actual = gameService.createNewGame(url);
		assertEquals(expected, actual);
	}

	@Test
	@Disabled
	void testMakeMove() {
		Long gameId = 1L;
		String url = "http://localhost:8080";
		Map<Integer, Integer> status;
		// @formatter:off
		status = new HashMap<Integer,Integer>() {
			{
				put(1, 6);put(2, 6);put(3, 6);put(4, 6);put(5, 6);put(6, 6);
				put(7, 0);
				put(8, 6);put(9, 6);put(10, 6);put(11, 6);put(12, 6);put(13, 6);
				put(14, 0);
			}
		};
		Map<Integer, Integer> statusExpected = new HashMap<Integer,Integer>() {
			{
				put(1, 0);put(2, 7);put(3, 7);put(4, 7);put(5, 7);put(6, 7);
				put(7, 1);
				put(8, 0);put(9, 6);put(10, 6);put(11, 6);put(12, 6);put(13, 6);
				put(14, 0);
			}
		};
		// @formatter:on
		int pitId = 1;
		Game game = new Game();
		game.setId(gameId);
		game.setPlayer(Player.FIRST_PLAYER);
		game.setBoard(status);
		when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
		when(gameRepository.save(game)).thenReturn(game);
		GameDTO expected = new GameDTO(gameId, url + "/games/" + gameId, statusExpected);

		GameDTO actual = gameService.makeMove(1L, pitId, url);

		verify(gamePlay).makeMove(game, pitId);
		assertEquals(expected, actual);
	}
}
