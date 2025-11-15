package edu.io;
import java.util.Scanner;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;
import edu.io.token.EmptyToken;
import edu.io.Board;

public class Main {
    public static void main(String[] args) {
        System.out.println("Uruchomienie gry Gold Rush.");

        Board board = new Board();
        PlayerToken playerToken = new PlayerToken(board); // Pionek tworzy się na (0, 0)
        // testowe umieszczenie żetonu złota
        Token gold = new GoldToken();

        board.placeToken(2, 1, gold);
        board.placeToken(3, 5, gold);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("{ GOLD RUSH: STEROWANIE }");
        System.out.println("W (góra), S (dół), A (lewo), D (prawo) lub Q (status).");
        while (true) {

            board.display();

            System.out.print("Podaj kierunek ruchu: ");
            input = scanner.nextLine().toUpperCase().trim();

            PlayerToken.Move moveDirection = PlayerToken.Move.NONE;

            switch (input) {
                case "W":
                    moveDirection = PlayerToken.Move.UP;
                    break;
                case "S":
                    moveDirection = PlayerToken.Move.DOWN;
                    break;
                case "A":
                    moveDirection = PlayerToken.Move.LEFT;
                    break;
                case "D":
                    moveDirection = PlayerToken.Move.RIGHT;
                    break;
                case "Q": // status
                    Board.Coords pos = playerToken.pos();
                    System.out.println("Aktualna pozycja: (" + pos.col() + ", " + pos.row() + ")");
                    continue; //wraca do początku pętli bez ruchu
                default:
                    System.out.println("Nieznany kierunek. Spróbuj ponownie.");
                    continue; //wraca do początku pętli bez ruchu
            }

            if (moveDirection != PlayerToken.Move.NONE) {
                try {
                    playerToken.move(moveDirection);
                    System.out.println("Ruch wykonany pomyślnie.");
                } catch (IllegalArgumentException e) {
                    System.err.println("BŁĄD: " + e.getMessage() + " Ruch nie został wykonany.");
                }
            }
        }

        // scanner.close();
    }
}
