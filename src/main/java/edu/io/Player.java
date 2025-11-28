package edu.io;

import edu.io.token.Token;
import edu.io.token.PlayerToken;
import edu.io.token.GoldToken;
import edu.io.token.PyriteToken;

public class Player {

    private double gold; // przechowuje złoto
    private PlayerToken token; // przechowuje ref do pionka

    public Player() {
        this.gold = 0.0; // zaczynamy bez złota
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return this.token;
    }

    public double gold() {
        return this.gold;
    }

    public void gainGold(double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Gain amount cannot be negative.");
        }
        this.gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Lose amount cannot be negative.");
        }
        if (this.gold - amount < 0.0) {
            this.gold = 0.0;
            throw new IllegalArgumentException("Gold cannot go below zero.");
        }
        this.gold -= amount;
    }

    public void interactWithToken(Token token) {
        //Pattern Matching do sprawdzenia żetonu
        if (token instanceof GoldToken goldToken) {
            this.gainGold(goldToken.amount()); //zbieranie złota
        }
    }
}