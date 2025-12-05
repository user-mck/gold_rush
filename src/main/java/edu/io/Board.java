package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {

    private final Token[][] grid;
    private static final int DEFAULT_SIZE = 10;
    private int nextCol = 0;
    private int nextRow = 0;

    public final int size;

    public record Coords(int col, int row) {}

    public Board() {
        this.size = DEFAULT_SIZE;
        this.grid = new Token[this.size][this.size];

        clean();
    }

    public int size() {
        return this.size;
    }

    public void placeToken(int col, int row, Token t) {
        this.grid[row][col] = t;
    }

    public Token peekToken(int col, int row) {
        return this.grid[row][col];
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

    public Coords getAvailableSquare() {
        if (nextRow >= size) {
            throw new IllegalStateException("Board is full.");
        }

        // zapisanie aktualnych współrzędnych
        Coords c = new Coords(nextCol, nextRow);

        // aktualizacja wskaźników do następnego pola
        nextCol++;
        if (nextCol >= size) {
            nextCol = 0;
            nextRow++;
        }
        return c;
    }
}