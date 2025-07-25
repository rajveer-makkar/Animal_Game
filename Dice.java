import java.util.Random;

public class Dice {
    private Random rand;

    public Dice() {
        rand = new Random();
    }

    public int roll() {
        return rand.nextInt(4) + 1;
    }

    public int randomSightings() {
        return rand.nextInt(4) + 1;
    }

    public boolean generateAnimal() {
        int chance = rand.nextInt(2);
        return chance == 1;
    }

    public int whichAnimal() {
        return rand.nextInt(5) + 1;
    }
}
