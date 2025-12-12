package edu.io.player;
import edu.io.token.*;

public class Player {
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();
    public final Vitals vitals = new Vitals();
    private PlayerToken token;

    public PlayerToken token() {return token;}
    public double gold() {return gold.amount();}
    public Shed shed(){return shed;}

    public void assignToken(PlayerToken token) {
        if (token == null) {
            throw new NullPointerException();
        }
        this.token = token;
    }

    public void interactWithToken(Token token) {

        if (!vitals.isAlive()) {
            throw new IllegalStateException("gracz nie żyje!");
        }

        if (token == null) {
            throw new NullPointerException();
        }

        switch (token) {
            case GoldToken goldToken -> {
                Tool currentTool = shed.getTool();
                double amount = goldToken.amount();

                currentTool.useWith(goldToken)
                        .ifWorking(() -> {
                            if (currentTool instanceof PickaxeToken pickaxe) {
                                gold.gain(amount * pickaxe.gainFactor());
                                System.out.println("Wydobyto złoto przy użyciu narzędzia!");
                            } else {
                                gold.gain(amount);
                                System.out.println("Wydobyto złoto!");
                            }
                        })
                        .ifBroken(() -> {
                            gold.gain(amount);
                            System.out.println("Wydobyto złoto!");
                            shed.dropTool();
                        })
                        .ifIdle(() -> {
                            gold.gain(amount);
                            System.out.println("Wydobyto złoto!");
                        });
                this.vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
            }

            case PickaxeToken pickaxeToken -> {
                shed.add(pickaxeToken);
                System.out.println("Otrzymano kilof!");
            }

            case AnvilToken anvilToken -> {
                if (shed.getTool() instanceof Repairable tool) {
                    tool.repair();
                    System.out.println("Narzędzie naprawione!");
                } else {
                    System.out.println("Brak narzędzia lub narzędzie nienaprawialne.");
                }
                this.vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
            }
            case EmptyToken emptyToken -> {
                this.vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }

            case WaterToken waterToken -> {
                vitals.hydrate(waterToken.amount());
            }

            default ->{
            }
        }
    }
}