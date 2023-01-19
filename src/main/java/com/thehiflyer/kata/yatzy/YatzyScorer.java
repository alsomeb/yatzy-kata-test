package com.thehiflyer.kata.yatzy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YatzyScorer {
	public int calculateScore(Category category, YatzyRoll roll) {
		int[] rollResultArray = roll.getDice();
		int totalScore = 0;

		switch (category) {
			case CHANCE -> totalScore = Arrays.stream(rollResultArray).sum();
			case YATZY -> totalScore = yatzyScore(rollResultArray);
			case PAIR -> totalScore = pairScore(rollResultArray);
			case THREE_KIND -> totalScore = threeKindScore(rollResultArray);
			case ACES, TWOS, THREES, FOURS, FIVES, SIXES -> totalScore = aceToSixScore(rollResultArray, category);
			case TWO_PAIR -> totalScore = twoPair(rollResultArray);
			case FOUR_OF_A_KIND -> totalScore = calculateFourOfAKind(rollResultArray);
			case FULL_HOUSE -> totalScore = calculateFullHouse(rollResultArray);
		}
		return totalScore;
	}

	private int calculateFullHouse(int[] rollResultArray) {
		int score = 0;

		// Vi Grupperar alla nummer i rollResultArray
		Map<Integer, Long> numbers = Arrays.stream(rollResultArray)
				.boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// Kolla om de finns minst 2 Entry (kåk, 2 par, 3 par tsm), om de gör, summera alla values i entry
		// tar ej TVÅPAR pga att det är en REST kvar i numbers, därav size = 3.
		if (numbers.size() == 2) {
			System.out.println("WORKS!");
			score += numbers.entrySet().stream()
					.mapToInt(val -> (val.getKey() * val.getValue().intValue())).sum();
		}

		System.out.println(numbers);
		System.out.println("Score: " + score);
		System.out.println("\n");


		return score;
	}

	private int calculateFourOfAKind(int[] rollResultArray) {
		int val = 0;
		Map<Integer, Long> diceCount = Arrays.stream(rollResultArray).boxed().collect(Collectors.groupingBy(keyValue -> keyValue, Collectors.counting()));
		for (Map.Entry<Integer, Long> valuePair : diceCount.entrySet()) {
			if (valuePair.getValue() >=4) {
				val += valuePair.getKey() * 4;
			}
		}
		return val;
	}

	public int yatzyScore(int[] rollResultArray) {
		Integer firstNr = rollResultArray[0];
		boolean allMatch = Arrays.stream(rollResultArray)
				.boxed()
				.allMatch(nr -> nr.equals(firstNr));

		return allMatch ? 50 : 0;
	}

	public int aceToSixScore(int[] rollResultArray, Category category) {
		int expectedValue = category.getVal();
		return Arrays.stream(rollResultArray)
				.filter(nr -> nr == expectedValue)
				.sum();
	}

	public int pairScore(int[] rollResultArray) {
		int score = 0;

		// Mappar Siffror och Antal förekommande
		Map<Integer, Long> numbers =
				Arrays.stream(rollResultArray)
						.boxed()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// Sorterar fram biggest nr från keys, med Collections.max
		int biggestNr = Collections.max(numbers.keySet());

		// När den hittar par (högsta siffran med par), score += key * 2
		for (Map.Entry<Integer, Long> set : numbers.entrySet()) {
			if(set.getValue() >= 2 && set.getKey() == biggestNr) {
				score += (set.getKey() * 2);
			}
		}

		System.out.println(numbers);
		return score;
	}

	public int threeKindScore(int[] rollResultArray) {
		int score = 0;

		// Mappar Siffror och Antal förekommande
		Map<Integer, Long> numbers =
				Arrays.stream(rollResultArray)
						.boxed()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// När den hittar three of a kind, value med minst 3, score += key * 3
		for (Map.Entry<Integer, Long> set : numbers.entrySet()) {
			if(set.getValue() >= 3) {
				score += (set.getKey() * 3);
			}
		}

		System.out.println(numbers);
		return score;
	}

	private int twoPair(int[] rollResultArray) {
		int score = 0;

		// Vi Grupperar alla nummer i rollResultArray
		Map<Integer, Long> numbers = Arrays.stream(rollResultArray)
				.boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// Sparar alla par till en ny map där alla values är minst 2 eller mer
		Map<Integer, Long> filteredPairs = numbers.entrySet().stream()
				.filter(key -> key.getValue() >= 2)
				.collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));


		// Kolla om de finns minst 2 Entry, om de gör, summera alla values i entry
		if (filteredPairs.size() >= 2) {
			score += filteredPairs.keySet().stream()
					.mapToInt(val -> val * 2).sum();
		}

		return score;
	}
}
