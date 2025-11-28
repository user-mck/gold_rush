package edu.io;

import edu.io.token.PlayerToken;

public class Game {

    private final Board board;
    private Player player; //na razie jeden gracz

    public Game() {
        this.board = new Board();
    }

    //wymagana do dołączenia gracza do gry
    public void join(Player player) {
        this.player = player;

        //utworzenie pionka (player, board)
        PlayerToken token = new PlayerToken(player, this.board);

        //przypisanie pionka do gracza
        player.assignToken(token);
    }

    public void start() {
        //przyszłe iteracje:
        // zaimplementować strukturę rozgrywki
        // wyczyścić main
    }
}