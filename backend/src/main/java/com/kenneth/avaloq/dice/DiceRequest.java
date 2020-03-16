package com.kenneth.avaloq.dice;

import java.util.Objects;

public class DiceRequest {

    private int diceCount;
    private int diceFaces;
    private int diceRollCount;

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getDiceFaces() {
        return diceFaces;
    }

    public void setDiceFaces(int diceFaces) {
        this.diceFaces = diceFaces;
    }

    public int getDiceRollCount() {
        return diceRollCount;
    }

    public void setDiceRollCount(int diceRollCount) {
        this.diceRollCount = diceRollCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceRequest that = (DiceRequest) o;
        return diceCount == that.diceCount &&
                diceFaces == that.diceFaces &&
                diceRollCount == that.diceRollCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diceCount, diceFaces, diceRollCount);
    }
}
