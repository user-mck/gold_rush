package edu.io;
import java.util.Scanner;
import edu.io.token.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Witajcie w Gold Rush!");
        Game game = new Game();
        Board board = new Board();
        Player player = new Player();
        game.join(player);
        game.start();
        PlayerToken playerToken = new PlayerToken(player, board);
        double playerGold = player.gold();

        // testowe umieszczenie żetonów
        //zaimplementować w późniejszych iteracjach w klasie Game**
        Token gold = new GoldToken();
        Token pyrite = new PyriteToken();
        board.placeToken(2, 1, gold);
        board.placeToken(3, 5, gold);
        board.placeToken(6, 6, pyrite);

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
                    System.out.println("Aktualna pozycja: (" + pos.col() + ", " + pos.row() + ")\n"+"Ilość złota: " + playerGold);
                    continue; //wraca do początku bez ruchu
                default:
                    System.out.println("Nieznany kierunek. Spróbuj ponownie.");
                    continue; //wraca do początku bez ruchu
            }

            //sprawdzić
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
