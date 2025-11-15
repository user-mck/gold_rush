package edu.io.token;
import edu.io.Board;
import edu.io.token.Token;

public class PlayerToken extends Token {

    private final Board board;
    private int col;
    private int row;

    public enum Move {
        NONE, UP, DOWN, LEFT, RIGHT
    }

    public PlayerToken(Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;

        // pozycja startowa (0, 0)
        this.col = 0;
        this.row = 0;

        // umieszczenie pionka na planszy
        this.board.placeToken(this.col, this.row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(this.col, this.row);
    }

    public void move(Move dir) {
        if (dir == Move.NONE) {
            return;
        }

        int newCol = this.col;
        int newRow = this.row;

        switch (dir) {
            case LEFT: newCol -= 1; break;
            case RIGHT: newCol += 1; break;
            case UP: newRow -= 1; break;
            case DOWN: newRow += 1; break;
            default: break;
        }

        // sprawdzanie granic
        if (newCol < 0 || newCol >= board.size ||
                newRow < 0 || newRow >= board.size)
        {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        // czyszczenie pola
        this.board.placeToken(this.row, this.col, new EmptyToken());

        // aktualizacja pozycji
        this.col = newCol;
        this.row = newRow;

        // przemieszczenie pionka
        this.board.placeToken(this.col, this.row, this);
    }
}