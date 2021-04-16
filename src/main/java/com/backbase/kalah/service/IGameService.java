package com.backbase.kalah.service;

import com.backbase.kalah.dto.GameDTO;
import com.backbase.kalah.dto.NewGameDTO;

public interface IGameService {
	public NewGameDTO createNewGame(String serverURL);

	public GameDTO makeMove(Long gameId, int pitId, String serverURL);
}
