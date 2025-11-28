package edu.io;
import edu.io.token.Token;
import edu.io.token.GoldToken;
import edu.io.token.PyriteToken;
import edu.io.token.PlayerToken;

public class Game {

    private final Board board;
    private Player player; //na razie jeden gracz

    public Game() {
        this.board = new Board();
    }

    //dołączenie gracza
    public void join(Player player) {
        this.player = player;
        PlayerToken token = new PlayerToken(player, this.board);
        player.assignToken(token);
    }

    public void start() {
        System.out.println("Witajcie w Gold Rush!");

        Token gold = new GoldToken();
        Token pyrite = new PyriteToken();

        this.board.placeToken(2, 1, gold);
        this.board.placeToken(3, 5, gold);
        this.board.placeToken(6, 6, pyrite);

        System.out.println("{ GOLD RUSH: STEROWANIE }");
        System.out.println("W (góra), S (dół), A (lewo), D (prawo) lub Q (status).");

    }
}