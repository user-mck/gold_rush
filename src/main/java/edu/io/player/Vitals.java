package edu.io.player;

public class Vitals {
    private int hydration = 100;
    private Runnable onDeathCallback;

    public Vitals() {
        this.onDeathCallback = () -> {};
    }

    public int hydration() { return hydration; }

    public boolean isAlive() { return hydration > 0; }

    public void hydrate(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.hydration = Math.min(100, hydration + amount);
    }

    public void dehydrate(int amount) {
        if (amount < 0) throw new IllegalArgumentException();

        int prevHydration = this.hydration;
        this.hydration = Math.max(0, hydration - amount);

        // callback, if hydration down to 0
        if (prevHydration > 0 && this.hydration == 0) {
            onDeathCallback.run();
        }
    }

    public void setOnDeathHandler(Runnable callback) {
        if (callback == null) {
            throw new NullPointerException("callback cannot be null");
        }
        this.onDeathCallback = callback;
    }
}