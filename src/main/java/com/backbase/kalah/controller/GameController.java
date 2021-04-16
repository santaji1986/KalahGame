package com.backbase.kalah.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.kalah.dto.GameDTO;
import com.backbase.kalah.dto.NewGameDTO;
import com.backbase.kalah.service.IGameService;

@RestController
@RequestMapping("/games")
public class GameController {
	private IGameService gameService;

	public GameController(IGameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public NewGameDTO createNewGame(HttpServletRequest request) {
		String serverURL = getUrl(request);
		System.out.println("serverURL : " + serverURL);
		return gameService.createNewGame(serverURL);
	}

	@PostMapping("/{gameId}/pits/{pitId}")
	public GameDTO makeMove(@PathVariable Long gameId, @PathVariable int pitId, HttpServletRequest request) {
		String serverURL = getUrl(request);
		return gameService.makeMove(gameId, pitId,serverURL);
	}

	private String getUrl(HttpServletRequest request) {
		final String url = request.getRequestURL().toString();
		final String uri = request.getRequestURI();
		final String host = url.substring(0, url.indexOf(uri));
		return host + request.getContextPath();

	}
}
