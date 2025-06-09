import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;

    private double health = 10;
    private double hunger = 10;
    private double sleep = 10;
    private double fun = 10;
    private double cleanliness = 10;

    public Pet(String name) {
        this.name = name;
    }

    // Getters
    public String getName() { return name; }

    public double getHealth() { return health; }
    public double getHunger() { return hunger; }
    public double getSleep() { return sleep; }
    public double getFun() { return fun; }
    public double getCleanliness() { return cleanliness; }


    private double round1Decimal(double value) {
      return Math.round(value * 10.0) / 10.0;
    }

    public void degradeStats() {
      health = Math.max(0, round1Decimal(health - 0.2));
      hunger = Math.max(0, round1Decimal(hunger - 0.2));
      sleep = Math.max(0, round1Decimal(sleep - 0.2));
      fun = Math.max(0, round1Decimal(fun - 0.2));
      cleanliness = Math.max(0, round1Decimal(cleanliness - 0.2));
    }

    // Actions
    public void eat() {
        hunger = Math.min(10, hunger + 0.5);
        cleanliness = Math.max(0, cleanliness - 0.2);
    }

    public void sleep() {
        this.sleep = Math.min(10, this.sleep + 0.5);
        fun = Math.max(0, fun - 0.2);
        hunger = Math.max(0, hunger - 0.2);
    }

    public void play() {
        fun = Math.min(10, fun + 0.5);
        sleep = Math.max(0, sleep - 0.2);
        hunger = Math.max(0, hunger - 0.2);
    }

    public void bathe() {
        cleanliness = Math.min(10, cleanliness + 0.5);
        fun = Math.max(0, fun - 0.2);
    }

    public void heal() {
        health = Math.min(10, health + 0.5);
        sleep = Math.max(0, sleep - 0.2);
        fun = Math.max(0, fun - 0.2);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", hunger=" + hunger +
                ", sleep=" + sleep +
                ", fun=" + fun +
                ", cleanliness=" + cleanliness +
                '}';
    }
}

