package com.kenneth.avaloq.dice;

import java.util.Map;

public class DiceResponse {

    private Map<Integer, Integer> sumTotal;

    public Map<Integer, Integer> getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Map<Integer, Integer> sumTotal) {
        this.sumTotal = sumTotal;
    }
}
