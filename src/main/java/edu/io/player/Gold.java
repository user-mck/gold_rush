package edu.io.player;

public class Gold {
    private double amount;

    public Gold() {
        this.amount = 0.0;
    }

    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gain amount must be positive");
        }
        this.amount += amount;
    }

    public void lose(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Lose amount must be positive");
        }
        if (this.amount - amount < 0) {
            this.amount = 0.0;
            throw new IllegalArgumentException("Gold cannot go below zero");
        }
        this.amount -= amount;
    }
}