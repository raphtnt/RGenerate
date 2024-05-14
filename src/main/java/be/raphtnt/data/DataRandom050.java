package be.raphtnt.data;

import java.util.Random;

public class DataRandom050 extends Data implements NotStr{

    @Override
    public String name() {
        return "Nombre aléatoire entre 0 et 50";
    }

    @Override
    public Object process() {
        Random random = new Random();
        return random.nextInt(51);
    }

}