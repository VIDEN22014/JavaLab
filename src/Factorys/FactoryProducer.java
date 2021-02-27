package Factorys;

import java.util.Random;

public class FactoryProducer {
   public static AbstractFactory getFactory() {
        int rand;
        Random random = new Random();
        rand = random.nextInt(2);
        switch (rand) {
            case (0):
                return new CommonBirdFactory();
            case (1):
                return new PredatorBirdFactory();
            default:
                throw new NullPointerException("Exeption");
        }
    }
}
