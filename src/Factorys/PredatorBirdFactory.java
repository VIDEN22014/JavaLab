package Factorys;

import Birds.Bird;
import Birds.PredatorBirds.*;

import java.util.Random;

public class PredatorBirdFactory extends AbstractFactory {
    @Override
    public Bird createBird() {
        int rand;
        Random random = new Random();
        rand = random.nextInt(2);
        switch (rand) {
            case (0):
                return new Eagle();
            case (1):
                return new Owl();
            default:
                throw new NullPointerException("Exeption");
        }
    }
}
