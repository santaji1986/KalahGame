package com.backbase.kalah.dto;

import java.util.Map;

import lombok.Value;

@Value
public class GameDTO {

	private Long id;

	private String url;

	private Map<Integer, Integer> status;
}
