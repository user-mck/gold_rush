package edu.io;
import java.util.Scanner;
import edu.io.player.*;
import edu.io.token.*;

public class Game {

    private final Board board;
    private Player player; //na razie jeden gracz

    public Game() {
        this.board = new Board();
    }
    private void floorSeparator(){System.out.println("__________________________________");}

    public void join(Player player) {
        this.player = player;
        PlayerToken token = new PlayerToken(player, this.board);
        player.assignToken(token);
    }

    private void currentTool(){
        Tool currentTool = player.shed().getTool();
        String toolStatus;

        if (currentTool instanceof NoTool) {
            toolStatus = "Brak";
        } else if (currentTool instanceof PickaxeToken pickaxe) {
            if (pickaxe.isBroken()) {
                toolStatus = "Zepsuty kilof! Wymaga naprawy lub usunięcia.";
            } else {
                int durability = pickaxe.durability();
                String repairable = (currentTool instanceof Repairable) ? " (Naprawialny)" : "";
                toolStatus = "Sprawny Kilof! (Pozostało użyć: " + durability + ")" + repairable;
            }
        } else {
            // placeholder do innych narzędzi
            toolStatus = "Posiadane narzędzie, nieokreślonego typu.";
        }

        System.out.println("Narzędzie: " + toolStatus);
    }

    private void playerStatus(){
        PlayerToken playerToken = player.token();
        Board.Coords pos = playerToken.pos();
        double playerGold = player.gold();
        int playerHydration = player.vitals.hydration();
        System.out.println("Aktualna pozycja: (" + pos.col() + ", " + pos.row() + ")");
        System.out.println("Nawodnienie Gracza: " + playerHydration);
        System.out.println("Zebrane złoto: " + playerGold);
    }

    public void tokenPlacement(){
        //nie powinienem tego rozdzielać, ale jest wygodniej
        Token gold = new GoldToken();
        Token pyrite = new PyriteToken();
        Token pickaxe = new PickaxeToken();
        Token anvil = new AnvilToken();
        Token water = new WaterToken();

        this.board.placeToken(2, 1, gold);
        this.board.placeToken(3, 5, gold);
        this.board.placeToken(6, 6, pyrite);
        this.board.placeToken(0, 2, pickaxe);
        this.board.placeToken(7, 8, anvil);
        this.board.placeToken(3, 2, water);
        this.board.placeToken(9, 4, water);
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        String input;

        PlayerToken playerToken = player.token();
        Board board = playerToken.getBoard();
        int boardSize = this.board.size();

        System.out.println("{ GOLD RUSH: STEROWANIE }");
        System.out.println("W (góra), S (dół), A (lewo), D (prawo) lub Q/E (status).");
        System.out.println("{ Wyświetlanie planszy: (Rozmiar: " + boardSize + "x" + boardSize + ") }");
        tokenPlacement();

        while (true) {
            floorSeparator();
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
                case "Q": // pozycja i złoto
                    playerStatus();
                    continue;
                case "E": // status narzędzia
                    currentTool();
                    continue;
                default:
                    System.out.println("Nieznany kierunek. Spróbuj ponownie.");
                    continue;
            }

            if (moveDirection != PlayerToken.Move.NONE) {
                try {
                    playerToken.move(moveDirection);
                    System.out.println("Ruch wykonany pomyślnie.");
                } catch (IllegalArgumentException e) {
                    System.err.println("BŁĄD: " + e.getMessage() + ", Ruch nie został wykonany.");
                }
            }
        }
        //scanner.close();
    }
}