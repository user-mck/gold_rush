package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;
import edu.io.token.EmptyToken;
import edu.io.token.Label;

public class Board {

    private final Token[][] grid;
    private static final int DEFAULT_SIZE = 10;

    public final int size;

    public record Coords(int col, int row) {}

    public Board() {
        this.size = DEFAULT_SIZE; // Inicjalizacja publicznego pola size
        this.grid = new Token[this.size][this.size];

        clean();
    }

    public int size() {
        return this.size;
    }

    public void placeToken(int col, int row, Token t) {
        this.grid[col][row] = t;
    }

    public Token peekToken(int col, int row) {
        return this.grid[col][row];
    }

    public void clean() {
        for (int c = 0; c < this.size; c++) {
            for (int r = 0; r < this.size; r++) {
                this.grid[c][r] = new EmptyToken();
            }
        }
    }


    public void display() {
        System.out.println("{ Wyświetlanie planszy: (Rozmiar: " + DEFAULT_SIZE + "x" + DEFAULT_SIZE + ") }");
        for (int c = 0; c < DEFAULT_SIZE; c++) {
            for (int r = 0; r < DEFAULT_SIZE; r++) {
                System.out.print("[" + grid[c][r].label() + "]");
            }
            System.out.println();
        }
    }
}