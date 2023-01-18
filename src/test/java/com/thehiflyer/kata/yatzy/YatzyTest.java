package com.thehiflyer.kata.yatzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class YatzyTest {

	private YatzyScorer yatzyScorer;

	@BeforeEach
	public void setUp() {
		yatzyScorer = new YatzyScorer();
	}

	@Test
	public void chanceScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(5, 2, 1, 4, 6));
		assertEquals(18, score);

		int score2 = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(1, 1, 3, 3, 6));
		assertEquals(14, score2);

		int score3 = yatzyScorer.calculateScore(Category.CHANCE, new YatzyRoll(4, 5, 5, 6, 1));
		assertEquals(21, score3);
	}

	@Test
	public void yatzyScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(5, 5, 5, 5, 5));
		assertEquals(50, score);

		int score4 = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1, 1, 1, 1, 1));
		assertEquals(50, score4);
	}

	@Test
	public void yatzyScoresNotCorrectly() {
		int score2 = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(5, 5, 5, 5, 1));
		assertEquals(0, score2);

		int score3 = yatzyScorer.calculateScore(Category.YATZY, new YatzyRoll(1, 1, 1, 2, 1));
		assertEquals(0, score3);
	}


	@Test
	public void acesScoresCorrectly() {
		// 1,1,1,4,5 placed on "ones" scores 1 + 1 + 1 = 3
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(1, 1, 1, 4, 5));
		assertEquals(3, score);

		// 2, 2, 1, 1, 2 places on "ones" scores 1 + 1 = 2
		int score2 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(2, 2, 1, 1, 2));
		assertEquals(2, score2);

		// 2, 2, 1, 2, 2 places on "ones" scores 1
		int score3 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(2, 2, 2, 1, 2));
		assertEquals(1, score3);
	}

	@Test
	public void acesScoresInCorrectly() {
		// 3,3,3,4,5 placed on "ones" scores 0
		int score = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(3, 3, 3, 4, 5));
		assertEquals(0, score);

		// 3,3,3,5,2 placed on "ones" scores 0
		int score2 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(3, 3, 3, 5, 2));
		assertEquals(0, score2);

		// 3,3,3,5,2 placed on "ones" scores 0, NOT CHOOSING THREES 3 + 3 + 3 = 9
		int score3 = yatzyScorer.calculateScore(Category.ACES, new YatzyRoll(3, 3, 3, 5, 2));
		assertNotEquals(9, score3);
	}

	@Test
	public void twosScoresCorrectly() {
		// 2,2,2,4,5 placed on "twos" scores 2 + 2 + 2 = 6
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 2, 4, 5));
		assertEquals(6, score);

		// 2, 2, 1, 1, 1 places on "twos" scores 2 + 2 = 4
		int score2 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 1, 1, 1));
		assertEquals(4, score2);

		// 1, 1, 2, 1, 1 places on "twos" scores 2
		int score3 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(1, 1, 2, 1, 1));
		assertEquals(2, score3);
	}

	@Test
	public void twosScoresCorrectlyIncorrectly() {
		// 2,2,2,4,4 placed on "twos" DOES NOT SUM all Twos = 6
		int score = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 2, 4, 4));
		assertNotEquals(4, score);

		// 2,2,2,4,4 placed on "twos" DOES NOT SUM all Fours = 8
		int score2 = yatzyScorer.calculateScore(Category.TWOS, new YatzyRoll(2, 2, 2, 4, 4));
		assertNotEquals(8, score2);
	}

	@Test
	public void threeScoresCorrectly() {
		// 3,3,3,4,5 placed on "threes" scores 3 + 3 + 3 = 9
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 3, 3, 4, 5));
		assertEquals(9, score);

		// 3,4,4,4,5 placed on "threes" scores 3
		int score2 = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 4, 4, 4, 5));
		assertEquals(3, score2);
	}

	@Test
	public void threeScoresInCorrectly() {
		// 3,3,3,4,5 placed on "threes" scores 3 + 3 + 3 = 9 not 3+3 = 6
		int score = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 3, 3, 4, 5));
		assertNotEquals(6, score);

		// 3,4,4,4,5 placed on "threes" not scores 0
		int score2 = yatzyScorer.calculateScore(Category.THREES, new YatzyRoll(3, 4, 4, 4, 5));
		assertNotEquals(0, score2);
	}

	@Test
	public void foursScoresCorrectly() {
		// 4,4,4,4,5 placed on "fours" scores 4 + 4 + 4 + 4 = 16
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4, 4, 4, 4, 5));
		assertEquals(16, score);

		// 3,4,4,4,5 placed on "fours" scores 4 + 4 + 4 = 12
		int score2 = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(3, 4, 4, 4, 5));
		assertEquals(12, score2);
	}

	@Test
	public void foursScoresInCorrectly() {
		// 4,4,4,4,5 placed on "fours" scores not minus -16
		int score = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(4, 4, 4, 4, 5));
		assertNotEquals(-16, score);

		// 3,4,4,4,5 placed on "fours" scores not 0
		int score2 = yatzyScorer.calculateScore(Category.FOURS, new YatzyRoll(3, 4, 4, 4, 5));
		assertNotEquals(0, score2);
	}

	@Test
	public void fivesScoresCorrectly() {
		// 4,5,5,5,5 placed on "fives" scores 5*4 = 20
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(4, 5, 5, 5, 5));
		assertEquals(20, score);

		// 3,4,4,5,5 placed on "fives" scores 5*2 = 10
		int score2 = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(3, 4, 4, 5, 5));
		assertEquals(10, score2);
	}

	@Test
	public void fivesScoresInCorrectly() {
		// 4,5,5,5,5 placed on "fives" scores 5*4 != -20
		int score = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(4, 5, 5, 5, 5));
		assertNotEquals(-20, score);

		// 3,4,4,5,5 placed on "fives" scores not 0
		int score2 = yatzyScorer.calculateScore(Category.FIVES, new YatzyRoll(3, 4, 4, 5, 5));
		assertNotEquals(0, score2);
	}

	@Test
	public void sixesScoresCorrectly() {
		// 6, 6, 6, 6, 6 placed on "sixes" scores 6*5 = 30
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 6, 6, 6, 6));
		assertEquals(30, score);

		// 3,4,4,6,5 placed on "SIXES" scores 6
		int score2 = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(3, 6, 4, 5, 5));
		assertEquals(6, score2);
	}

	@Test
	public void sixesScoresInCorrectly() {
		// 6, 6, 6, 6, 6 placed on "sixes" scores 6*5 = 30 NOT YATZY 50 p
		int score = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 6, 6, 6, 6));
		assertNotEquals(50, score);

		// 6, 6, 6, 6, 6 placed on "sixes" scores 6*5 = 30 NOT 0 p
		int score2 = yatzyScorer.calculateScore(Category.SIXES, new YatzyRoll(6, 6, 6, 6, 6));
		assertNotEquals(0, score2);
	}

	@Test
	public void pairScoresCorrectly() {
		// 3,3,3,4,4 scores 8 (4+4)
		int score = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(3, 3, 3, 4, 4));
		assertEquals(8, score);

		// 1,1,6,2,6 scores 12 (6+6)
		int score2 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 1, 6, 2, 6));
		assertEquals(12, score2);

		// 1,1,3,3,3 scores 6 (3+3)
		int score3 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 1, 3, 3, 3));
		assertEquals(6, score3);

		// 3,3,3,3,1 scores 6 (3+3)
		int score4 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(3, 3, 3, 3, 1));
		assertEquals(6, score4);
	}

	@Test
	public void pairScoresInCorrectly() {
		// 3,3,3,4,4 scores 8 (4+4) - INTE tar minsta par 3, 3
		int score = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(3, 3, 3, 4, 4));
		assertNotEquals(6, score);

		// 1,1,6,2,6 scores 12 (6+6) - INTE tar minsta par 1, 1
		int score2 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 1, 6, 2, 6));
		assertNotEquals(2, score2);

		// 1,1,3,3,3 scores 6 (3+3) - INTE tar minsta par 1, 1
		int score3 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(1, 1, 3, 3, 3));
		assertNotEquals(2, score3);

		// 3,3,3,3,1 scores 6 (3+3) - INTE blir 0
		int score4 = yatzyScorer.calculateScore(Category.PAIR, new YatzyRoll(3, 3, 3, 3, 1));
		assertNotEquals(0, score4);
	}

	@Test
	public void threeOfAKindScoresCorrectly() {
		// 3,3,3,4,5 scores 9 (3+3+3)
		int score = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 4, 5));
		assertEquals(9, score);

		// 3,3,3,3,1 scores 9 (3+3+3)
		int score2 = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 3, 1));
		assertEquals(9, score2);

		// 3,3,3,3,4 scores 9 (3+3+3)
		int score3 = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 4, 4));
		assertEquals(9, score3);
	}

	@Test
	public void threeOfAKindScoresCorrectlyIncorrectly() {
		// 3,3,3,4,5 scores 9 (3+3+3) - INTE 0 av ngn anledning
		int score = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 4, 5));
		assertNotEquals(0, score);

		// 3,3,3,3,1 scores 9 (3+3+3) - INTE 16 av ngn anledning
		int score2 = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 3, 1));
		assertNotEquals(16, score2);

		// 3,3,3,3,4 scores 9 (3+3+3) - INTE 8, 4 + 4
		int score3 = yatzyScorer.calculateScore(Category.THREE_KIND, new YatzyRoll(3, 3, 3, 4, 4));
		assertNotEquals(8, score3);
	}


	@Test
	public void twoPairsScoresCorrect() {
		// 1,1,2,3,3 scores 8 (1+1+3+3)
		int score = yatzyScorer.calculateScore(Category.TWO_PAIR, new YatzyRoll(1, 1, 2, 3, 3));
		assertEquals(8, score);

		// 1,1,2,2,2 scores 6 (1+1+2+2)
		int score2 = yatzyScorer.calculateScore(Category.TWO_PAIR, new YatzyRoll(1, 1, 2, 2, 2));
		assertEquals(6, score2);

		// 1,1,2,3,4 scores 0
		int score3 = yatzyScorer.calculateScore(Category.TWO_PAIR, new YatzyRoll(1, 1, 2, 3, 4));
		assertEquals(0, score3);
	}

	@Test
	public void twoPairsScoresInCorrect() {
		// should NOT be 2 ( 1 + 1 )
		int score = yatzyScorer.calculateScore(Category.TWO_PAIR, new YatzyRoll(1, 1, 2, 1, 3));
		assertNotEquals(2, score);

		// should NOT be 9 ( 1 + 1 + 1, 3 + 3)
		int score2 = yatzyScorer.calculateScore(Category.TWO_PAIR, new YatzyRoll(1, 1, 1, 3, 3));
		assertNotEquals(9, score2);
	}
	

 // TODO Four of a kind, Small straight, Large straight, Full house
}
