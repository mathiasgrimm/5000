package com.mathiasgrimm._5000.api;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DiceGeneratorTest {

    @Test()
    public void sizeHasToBePositive() {
        DiceGenerator diceGenerator = new DiceGenerator();

        try {
            diceGenerator.generate(0);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("size has to be a positive number", e.getMessage());
        }

        try {
            diceGenerator.generate(-1);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("size has to be a positive number", e.getMessage());
        }

        try {
            diceGenerator.generate(null);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals("size has to be a positive number", e.getMessage());
        }
    }

    @Test
    public void itGeneratesTheCorrectSize() throws Exception {
        DiceGenerator diceGenerator = new DiceGenerator();

        List<Integer> dices = diceGenerator.generate(1);
        TestCase.assertEquals(1, dices.size());

        dices = diceGenerator.generate(10);
        TestCase.assertEquals(10, dices.size());
    }

    @Test
    public void itGeneratesWithinTheRange() throws Exception {
        DiceGenerator diceGenerator = new DiceGenerator();
        List<Integer> dices = diceGenerator.generate(1_000_000);

        for (Integer dice : dices) {
            TestCase.assertTrue(dice > 0 && dice <= 6);
        }
    }

    @Test
    public void itGeneratesRandomNumbers() throws Exception {
        DiceGenerator diceGenerator = new DiceGenerator();
        List<Integer> dices = diceGenerator.generate(1_000_000);

        Map<Integer, Integer> groupByDice = new Hashtable<>();

        for (Integer dice : dices) {
            if (!groupByDice.containsKey(dice)) {
                groupByDice.put(dice, 0);
            }

            groupByDice.put(dice, groupByDice.get(dice) + 1);
        }

        TestCase.assertTrue(groupByDice.size() > 1);
        TestCase.assertTrue(groupByDice.containsKey(1));
        TestCase.assertTrue(groupByDice.containsKey(2));
        TestCase.assertTrue(groupByDice.containsKey(3));
        TestCase.assertTrue(groupByDice.containsKey(4));
        TestCase.assertTrue(groupByDice.containsKey(5));
        TestCase.assertTrue(groupByDice.containsKey(6));
    }
}
