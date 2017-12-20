package com.mathiasgrimm._5000.api;

import junit.framework.TestCase;
import org.junit.Test;

public class ScoreTest {

    @Test
    public void defaultPointsIs0() {
        Score score = new Score();
        TestCase.assertEquals(0, (int) score.getPoints());
    }

    @Test
    public void defaultStartIsFalse() {
        Score score = new Score();
        TestCase.assertEquals(0, (int) score.getPoints());
    }

    @Test
    public void itAddPointsCorrectly() {

        Score score = new Score();

        score.setPoints(1);
        score.addPoints(100);

        TestCase.assertEquals(101, (int) score.getPoints());
    }
}
