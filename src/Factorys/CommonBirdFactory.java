package Factorys;

import Birds.Bird;
import Birds.CommonBirds.*;

import java.util.Random;

public class CommonBirdFactory extends AbstractFactory {
    @Override
    public Bird createBird() {
        int rand;
        Random random = new Random();
        rand = random.nextInt(2);
        switch (rand) {
            case (0):
                return new Pigeon();
            case (1):
                return new Sparrow();
            default:
                throw new NullPointerException("Exeption");
        }
    }
}
