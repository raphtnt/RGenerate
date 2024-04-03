package be.raphtnt.data;

import java.util.Random;

public class DataRandom010 extends Data implements NotStr{

    @Override
    public String name() {
        return "Nombre aléatoire entre 0 et 10";
    }

    @Override
    public Object process() {
        Random random = new Random();
        return random.nextInt(11);
    }

}