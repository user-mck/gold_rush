package edu.io;

public class Board {
    public final int size;
    // na razie jest stały rozmiar planszy - 10x10
    private static final int DEFAULT_SIZE = 10;
    private final Token[][] grid;

    public Board() {
        this.size = DEFAULT_SIZE;
        this.grid = new Token[this.size][this.size];
        clean();
    }

    public int size() {
        return DEFAULT_SIZE;
    }

    public void placeToken(int col, int row, Token t) {
        this.grid[col][row] = t;
    }

    public Token square(int row, int col) {
        return this.grid[row][col];
    }

    public void clean() {
        for (int r = 0; r < DEFAULT_SIZE; r++) {
            for (int c = 0; c < DEFAULT_SIZE; c++) {
                // wypełnienie wszystkich pól empty_token_label
                this.grid[r][c] = new EmptyToken();
            }
        }
    }

    public void display() {
        System.out.println("{ Wyświetlanie planszy: Board (Rozmiar: " + DEFAULT_SIZE + "x" + DEFAULT_SIZE + ") }");
        for (int r = 0; r < DEFAULT_SIZE; r++) {
            for (int c = 0; c < DEFAULT_SIZE; c++) {
                System.out.print("[" + grid[r][c].label() + "]");
            }
            System.out.println();
        }
    }
}