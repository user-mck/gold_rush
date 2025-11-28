package edu.io;
import java.util.Scanner;
import edu.io.token.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();

        PlayerToken playerToken = player.token();
        Board board = playerToken.getBoard();

        Scanner scanner = new Scanner(System.in);
        String input;

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
                    double playerGold = player.gold();
                    System.out.println("Aktualna pozycja: (" + pos.col() + ", " + pos.row() + ")\n"+"Zebrane złoto: " + playerGold);
                    continue;
                default:
                    System.out.println("Nieznany kierunek. Spróbuj ponownie.");
                    continue;
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

        //scanner.close();
    }
}