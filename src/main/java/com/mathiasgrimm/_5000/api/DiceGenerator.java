package com.mathiasgrimm._5000.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class DiceGenerator {
    public List<Integer> generate(Integer size) throws Exception {
        if (size == null || size < 1) {
            throw new Exception("size has to be a positive number");
        }

        int min = 1;
        int max = 6;

        List<Integer> dices = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            dices.add(ThreadLocalRandom.current().nextInt(min, max + 1));
        }

        return dices;
    }
}
