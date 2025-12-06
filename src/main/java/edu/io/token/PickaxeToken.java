package edu.io.token;
import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {

    private final double initialGainFactor;
    private final int initialDurability;
    private int currentDurability;

    // Konstruktor domyślny
    public PickaxeToken() {
        this(1.5, 3); // Domyślny gainFactor 1.5, durability 3 [12, 13]
    }

    // Konstruktor z własnym gainFactorem
    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    // Pełny konstruktor
    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0 || durability <= 0) {
            throw new IllegalArgumentException("Factors must be positive");
        }
        this.initialGainFactor = gainFactor;
        this.initialDurability = durability;
        this.currentDurability = durability;
    }

    public double gainFactor() {
        return initialGainFactor;
    }

    public int durability() {
        return currentDurability;
    }

    // Zużycie kilofa
    public void use() {
        if (!isBroken()) {
            this.currentDurability--;
        }
    }

    @Override
    public boolean isBroken() {
        return currentDurability <= 0;
    }

    @Override
    public void repair() {
        this.currentDurability = this.initialDurability;
    }

    // Implementacja Fluent Interface (uproszczona)
    // Zmienne stanu do obsługi łańcucha wywołań
    private boolean interactionSuccessful = false;
    private Token usedOnToken = null;

    @Override
    public Tool useWith(Token withToken) {
        this.usedOnToken = withToken;
        // Tylko interakcja ze złotem jest "Working" [19]
        if (!isBroken() && withToken instanceof GoldToken) {
            interactionSuccessful = true;
            use(); // Zużyj kilof, jeśli użyto go na złocie [5]
        } else if (isBroken() && withToken instanceof GoldToken) {
            interactionSuccessful = false;
        } else {
            interactionSuccessful = false;
        }
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        // Working oznacza udane użycie na złocie (przed sprawdzeniem, czy się nie zepsuł)
        if (!isBroken() && interactionSuccessful && usedOnToken instanceof GoldToken) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        // Broken oznacza, że jest już zepsuty (lub zużył się właśnie teraz) [20]
        if (isBroken() && usedOnToken instanceof GoldToken) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        // Idle oznacza, że użyto na czymś innym niż złoto [19]
        if (!(usedOnToken instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}