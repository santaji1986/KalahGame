package com.backbase.kalah.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.backbase.kalah.GlobalConstants;
import com.backbase.kalah.GlobalConstants.Player;
import com.backbase.kalah.exception.InvalidPitException;
import com.backbase.kalah.model.Game;

@Service
public class GamePlay implements IGamePlay {

	@Override
	public Game makeMove(Game game, int pitId) {
		validatePitNumber(game, pitId);
		Map<Integer, Integer> board = game.getBoard();
		Integer stoneCount = board.get(pitId);
		board.replace(pitId, 0);
		int lastIndex = pitId + stoneCount;
		Player player = game.getPlayer();
		for (int i = pitId + 1; i <= lastIndex; i++) {
			if (i == GlobalConstants.LAST_PIT) {
				i = 0;
				lastIndex = lastIndex - GlobalConstants.LAST_PIT;
			}

			if (player == GlobalConstants.Player.FIRST_PLAYER && i == GlobalConstants.SECOND_PLAYER_KALAH) {
				continue;
			}
			if (player == GlobalConstants.Player.SECOND_PLAYER && i == GlobalConstants.FIRST_PLAYER_KALAH) {
				continue;
			}
			board.replace(i, board.get(i) + 1);
		}
		return game;
	}

	private void validatePitNumber(Game game, int pitId) {
		if (pitId == GlobalConstants.FIRST_PLAYER_KALAH || pitId == GlobalConstants.SECOND_PLAYER_KALAH)
			throw new InvalidPitException("Kalah pit is not allowed for making move.");
		
		Player player = game.getPlayer();
		if (player == GlobalConstants.Player.FIRST_PLAYER) {
			if (!(pitId >= GlobalConstants.FIRST_PIT && pitId < GlobalConstants.FIRST_PLAYER_KALAH))
				throw new InvalidPitException("It's not your turn.");
		} else if (!(pitId > GlobalConstants.FIRST_PLAYER_KALAH && pitId < GlobalConstants.SECOND_PLAYER_KALAH))
			throw new InvalidPitException("It's not your turn.");

		if (game.getBoard().get(pitId) == 0)
			throw new InvalidPitException("empty pit selection is not allowed.");
	}
}
