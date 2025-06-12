import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;

    private double health = 8;
    private int level = 1;

    private double hunger = 10;
    private double sleep = 10;
    private double fun = 10;
    private double cleanliness = 10;

    public Pet(String name) {
        this.name = name;
    }

    // Getters
    public String getName() { return name; }

    public double getLevel() { return level; }
    public double getHealth() { return health; }
    public double getHunger() { return hunger; }
    public double getSleep() { return sleep; }
    public double getFun() { return fun; }
    public double getCleanliness() { return cleanliness; }


    private double round1Decimal(double value) {
      return Math.round(value * 10.0) / 10.0;
    }

    public void degradeStats() {
      //health = Math.max(0, round1Decimal(health - 0.2));
      hunger = Math.max(0, round1Decimal(hunger - 1));
      sleep = Math.max(0, round1Decimal(sleep - 0.5));
      fun = Math.max(0, round1Decimal(fun - 0.5));
      cleanliness = Math.max(0, round1Decimal(cleanliness - 0.5));
    }

    // Actions
    public void eat() {
        hunger = Math.min(10, hunger + 1);
        cleanliness = Math.max(0, cleanliness - 0.5);
    }

    public void sleep() {
        sleep = Math.min(10, sleep + 1);
        hunger = Math.max(0, hunger - 0.5);
    }

    public void play() {
        fun = Math.min(10, fun + 1);
        sleep = Math.max(0, sleep - 0.5);
    }

    public void bathe() {
        cleanliness = Math.min(10, cleanliness + 1);
        fun = Math.max(0, fun - 0.5);
    }

    public void levelUp(){
      level++;
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
