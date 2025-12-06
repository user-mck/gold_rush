package edu.io.token;
import edu.io.Board;
import edu.io.player.Player;

public class PlayerToken extends Token {

    private final Board board;
    private final Player player;
    private int col, row;

    public enum Move {
        NONE, UP, DOWN, LEFT, RIGHT
    }

    public Board getBoard() {
        return this.board;
    }

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.player = player;

        Board.Coords initialPos = board.getAvailableSquare();
        this.col = initialPos.col();
        this.row = initialPos.row();

        this.board.placeToken(this.col, this.row, this);

        player.assignToken(this);
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

        if (newCol < 0 || newCol >= board.size ||
                newRow < 0 || newRow >= board.size)
        {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        var token = board.peekToken(newCol, newRow);
        player.interactWithToken(token);
        board.placeToken(col, row, new EmptyToken());

        this.col = newCol;
        this.row = newRow;

        board.placeToken(col, row, this);
    }
}