package com.backbase.kalah.service;

import com.backbase.kalah.model.Game;

public interface IGamePlay {
	Game makeMove(Game game, int pitId);
}
