package edu.io;
import java.util.Scanner;
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

        Scanner scanner = new Scanner(System.in);
        String input;

        PlayerToken playerToken = player.token();
        Board board = playerToken.getBoard();

        System.out.println("Witajcie w Gold Rush!");

        Token gold = new GoldToken();
        Token pyrite = new PyriteToken();

        this.board.placeToken(2, 1, gold);
        this.board.placeToken(3, 5, gold);
        this.board.placeToken(6, 6, pyrite);

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
                    double playerGold = player.gold();
                    System.out.println("Aktualna pozycja: (" + pos.col() + ", " + pos.row() + ")\n"+"Zebrane złoto: " + playerGold);
                    continue;
                default:
                    System.out.println("Nieznany kierunek. Spróbuj ponownie.");
                    continue;
            }

            //chck
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