package edu.io.token;

public class WaterToken extends Token {
    private final int amount;

    public WaterToken(int amount) {
        super(Label.WATER_TOKEN_LABEL);

        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Water amount must be between 0 and 100.");
        }
        this.amount = amount;
    }

    public WaterToken() {
        this(10);
    }

    public int amount() {
        return amount;
    }
}