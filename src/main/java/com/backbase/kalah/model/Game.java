package com.backbase.kalah.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.backbase.kalah.GlobalConstants;

import lombok.Data;

@Entity
@Table(name = "Games")
@Data
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	@MapKeyColumn(name = "pitId")
	@Column(name = "value")
	@JoinTable(name = "Game_Board", joinColumns = { @JoinColumn(name = "GAME_ID", referencedColumnName = "ID") })
	Map<Integer, Integer> board;

	@Enumerated(value = EnumType.STRING)
	private GlobalConstants.Status status;

	@Enumerated(value = EnumType.STRING)
	private GlobalConstants.Player player;

	public Game() {
		board = new HashMap<>();
		int firstKhalIndex = GlobalConstants.FIRST_PLAYER_KALAH;
		int secondKhalIndex = GlobalConstants.SECOND_PLAYER_KALAH;
		for (int i = GlobalConstants.FIRST_PIT; i <= GlobalConstants.LAST_PIT; i++) {
			int value = (i != firstKhalIndex && i != secondKhalIndex) ? GlobalConstants.DEFAULT_STONES_QUANTITY : 0;
			board.put(i, value);
		}

		status = GlobalConstants.Status.IN_PROGRESS;
		player = GlobalConstants.Player.FIRST_PLAYER;
	}

}
