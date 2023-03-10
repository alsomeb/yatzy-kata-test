package com.thehiflyer.kata.yatzy;

public enum Category {
	CHANCE(0),
	// add more categories here as you implement them
	YATZY(0),
	ACES(1),
	TWOS(2),
	THREES(3),
	FOURS(4),
	FIVES(5),
	SIXES(6),
	PAIR(0),
	THREE_KIND(0),
	TWO_PAIR(0),
	FOUR_OF_A_KIND(0),
	FULL_HOUSE(0),
	SMALL_STRAIGHT(15),
	LARGE_STRAIGHT(20);

	private final int val;

	Category(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
