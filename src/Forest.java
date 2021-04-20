import java.util.ArrayList;
import java.util.List;

import Birds.*;
import Factorys.*;

public class Forest {
    List<Bird> birdList = new ArrayList<Bird>();
    FactoryProducer factoryProducer = new FactoryProducer();

    void create() {
        birdList.add(factoryProducer.getFactory().createBird());
    }

    void checkDanger() {
        if (birdList.get(birdList.size() - 1) instanceof PredatorBird) {
            for (Bird i : birdList) {
                i.danger();
            }
            birdList.get(birdList.size() - 1).down();
        }
    }
}
