package com.backbase.kalah.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.backbase.kalah.dto.GameDTO;
import com.backbase.kalah.dto.NewGameDTO;
import com.backbase.kalah.service.IGameService;

@WebMvcTest(GameController.class)
class GameControllerTest {

	@MockBean
	private IGameService gameService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Create Game")
	void testCreateNewGame() throws Exception {
		String json = "{\"id\": 1,\"url\": \"http://localhost:8080/games/1\"}";

		String url = "http://localhost:8080";
		NewGameDTO expected = new NewGameDTO(1L, url + "/games/" + 1L);
		when(gameService.createNewGame(url)).thenReturn(expected);

		// @formatter:off
		this.mockMvc.perform(
				post("/games")
			)
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(print())
			.andExpect(content().json(json ));
	// @formatter:on
	}

	@Test
	@DisplayName("Make a move")
	void testMakeMove() throws Exception {
		String url = "http://localhost:8080";
		Long gameId = 1L;
		Map<Integer, Integer> statusExpected = new HashMap<Integer, Integer>() {
			{
				put(1, 0);
				put(2, 7);
				put(3, 7);
				put(4, 7);
				put(5, 7);
				put(6, 7);
				put(7, 1);
				put(8, 0);
				put(9, 6);
				put(10, 6);
				put(11, 6);
				put(12, 6);
				put(13, 6);
				put(14, 0);
			}
		};
		GameDTO expected = new GameDTO(gameId, url + "/games/" + gameId, statusExpected);
		when(gameService.makeMove(1L, 1, url)).thenReturn(expected);

		String json = null;
		// @formatter:off
		this.mockMvc.perform(
				post("/games/1/pits/1")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.game.id").value("1"));
			 
	// @formatter:on
	}

}
