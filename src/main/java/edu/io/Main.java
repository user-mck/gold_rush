package edu.io;

public class Main {
    public static void main(String[] args) {
        System.out.println("Uruchomienie gry Gold Rush.");

        Board gameBoard = new Board();

        // testowe umieszczenie żetonu złota i gracza
        Token gold = new GoldToken();
        Token player = new PlayerToken(gameBoard);

        gameBoard.placeToken(1, 1, gold);
        gameBoard.placeToken(2, 2, player);

        gameBoard.display();
    }
}
