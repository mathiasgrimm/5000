package com.mathiasgrimm._5000.api;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

class ScoreCalculator {

    protected void validateDices(List<Integer> dices) throws Exception {
        if (dices == null || dices.size() > 5) {
            throw new Exception("invalid dices input");
        }

        for (Integer dice : dices) {
            if (dice < 1 || dice > 6) {
                throw new Exception("dice [" + dice + "] out of range 1-6");
            }
        }
    }

    public Score calculate(List<Integer> dices) throws Exception {
        System.out.println(dices);
        this.validateDices(dices);

        Score score = new Score();
        Integer dice = null;

        if ((dice = this.popGroup(5, dices)) != null) {
            score.setStar(true);

            int multiplier = 1000;

            if (dice == 1) {
                multiplier = 10000;
            }

            score.setPoints(dice * multiplier);
        } else if ((dice = this.popGroup(3, dices)) != null) {
            int multiplier = 100;

            if (dice == 1) {
                multiplier = 1000;
                score.setStar(true);
            }

            score.setPoints(dice * multiplier);
        }

        for (int i = 0; i < dices.size(); i++) {
            dice = dices.get(i);
            if (dice == 1) {
                score.addPoints(100);
                dices.remove(i);
                i = -1;
            } else if (dice == 5) {
                score.addPoints(50);
                dices.remove(i);
                i = -1;
            }
        }

        if (dices.size() > 0 && score.getPoints() > 0) {
            score.setRest(dices);
        }

        return score;
    }

    protected Integer popGroup(Integer size, List<Integer> dices) {
        Integer dice = null;
        Map<Integer, Integer> groups = this.groupByDice(dices);

        for (Integer key : groups.keySet()) {
            if (groups.get(key) >= size) {
                dice = key;
                break;
            }
        }

        if (dice == null) {
            return null;
        }

        int popped = 0;
        for (int i = 0; i < dices.size() && popped < size; i++) {
            if (dices.get(i) == dice) {
                dices.remove(i);
                i = -1;
                popped++;
            }
        }

        return dice;
    }

    protected Map<Integer, Integer> groupByDice(List<Integer> dices) {
        Map<Integer, Integer> groups = new Hashtable<>();

        for (Integer dice : dices) {
            if (!groups.containsKey(dice)) {
                groups.put(dice, 0);
            }

            groups.put(dice, groups.get(dice) + 1);
        }

        return groups;
    }
}
