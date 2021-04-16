package com.backbase.kalah;

public class GlobalConstants {
	public static final int DEFAULT_STONES_QUANTITY = 6;

	public static final int FIRST_PIT = 1;
	public static final int LAST_PIT = 14;

	public static final int FIRST_PLAYER_KALAH = 7;
	public static final int SECOND_PLAYER_KALAH = 14;

	public enum Player {
		FIRST_PLAYER, SECOND_PLAYER;
	}

	public enum Status {
		IN_PROGRESS, FIRST_PLAYER_WIN, SECOND_PLAYER_WIN, DRAW
	}
}
