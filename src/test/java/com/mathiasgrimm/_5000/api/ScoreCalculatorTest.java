package com.mathiasgrimm._5000.api;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ScoreCalculatorTest {

    private List<Integer> getDices(String dices) {
        List<Integer> dicesList = new ArrayList<>();

        String[] dicesStringArray = dices.split("\\s");

        for (int i = 0; i < dicesStringArray.length; i++) {
            dicesList.add(Integer.valueOf(dicesStringArray[i]));
        }

        return dicesList;
    }

    @Test
    public void itDoesNotAcceptNull() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        try {
            scoreCalculator.calculate(null);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("invalid dices input", e.getMessage());
        }
    }

    @Test
    public void itAcceptsMaxOf5Dices() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        try {
            scoreCalculator.calculate(this.getDices("1 2 3 4 5 6"));
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("invalid dices input", e.getMessage());
        }
    }

    @Test
    public void itValidatesRange() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();

        try {
            scoreCalculator.calculate(this.getDices("0"));
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("dice [0] out of range 1-6", e.getMessage());
        }

        try {
            scoreCalculator.calculate(this.getDices("7"));
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("dice [7] out of range 1-6", e.getMessage());
        }
    }

    @Test
    public void itCalculatesCorrectlyForFiveOfTheSameDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 10000 star: true rest: []", calculator.calculate(getDices("1 1 1 1 1")).toString());
        assertEquals("points: 6000 star: true rest: []", calculator.calculate(getDices("6 6 6 6 6")).toString());
        assertEquals("points: 5000 star: true rest: []", calculator.calculate(getDices("5 5 5 5 5")).toString());
        assertEquals("points: 4000 star: true rest: []", calculator.calculate(getDices("4 4 4 4 4")).toString());
        assertEquals("points: 3000 star: true rest: []", calculator.calculate(getDices("3 3 3 3 3")).toString());
        assertEquals("points: 2000 star: true rest: []", calculator.calculate(getDices("2 2 2 2 2")).toString());
    }

    @Test
    public void itCalculatesCorrectlyForThreeOfTheSameDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 1000 star: true rest: []", calculator.calculate(getDices("1 1 1")).toString());
        assertEquals("points: 200 star: false rest: []", calculator.calculate(getDices("2 2 2")).toString());
        assertEquals("points: 300 star: false rest: []", calculator.calculate(getDices("3 3 3")).toString());
        assertEquals("points: 400 star: false rest: []", calculator.calculate(getDices("4 4 4")).toString());
        assertEquals("points: 500 star: false rest: []", calculator.calculate(getDices("5 5 5")).toString());
        assertEquals("points: 600 star: false rest: []", calculator.calculate(getDices("6 6 6")).toString());

        assertEquals("points: 600 star: false rest: [2 2]", calculator.calculate(getDices("6 6 6 2 2")).toString());
        assertEquals("points: 700 star: false rest: []", calculator.calculate(getDices("6 6 6 5 5")).toString());
        assertEquals("points: 650 star: false rest: [4]", calculator.calculate(getDices("6 6 6 5 4")).toString());
    }

    @Test
    public void itCalculatesCorrectlyForTwoOfTheSameDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 200 star: false rest: []", calculator.calculate(getDices("1 1")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("2 2")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("3 3")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("4 4")).toString());
        assertEquals("points: 100 star: false rest: []", calculator.calculate(getDices("5 5")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("6 6")).toString());
    }

    @Test
    public void itCalculatesCorrectlyForASingleDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 100 star: false rest: []", calculator.calculate(getDices("1")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("2")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("3")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("4")).toString());
        assertEquals("points: 50 star: false rest: []", calculator.calculate(getDices("5")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("6")).toString());
    }

    @Test
    public void itCalculatesCorrectlyForFourOfTheSameDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 1100 star: true rest: []", calculator.calculate(getDices("1 1 1 1")).toString());
        assertEquals("points: 200 star: false rest: [2]", calculator.calculate(getDices("2 2 2 2")).toString());
        assertEquals("points: 300 star: false rest: [3]", calculator.calculate(getDices("3 3 3 3")).toString());
        assertEquals("points: 400 star: false rest: [4]", calculator.calculate(getDices("4 4 4 4")).toString());
        assertEquals("points: 550 star: false rest: []", calculator.calculate(getDices("5 5 5 5")).toString());
        assertEquals("points: 600 star: false rest: [6]", calculator.calculate(getDices("6 6 6 6")).toString());

        assertEquals("points: 1150 star: true rest: []", calculator.calculate(getDices("1 1 1 1 5")).toString());
        assertEquals("points: 1100 star: true rest: [4]", calculator.calculate(getDices("1 1 1 1 4")).toString());
    }

    @Test
    public void itCalculatesCorrectlyForTwoAndTwoOfTheSameDice() throws Exception {

        ScoreCalculator calculator = new ScoreCalculator();

        assertEquals("points: 300 star: false rest: [3]", calculator.calculate(getDices("1 1 5 5 3")).toString());
        assertEquals("points: 0 star: false rest: []", calculator.calculate(getDices("2 2 3 3 4")).toString());
        assertEquals("points: 100 star: false rest: [2 2 3 3]", calculator.calculate(getDices("1 2 2 3 3")).toString());
    }
}
