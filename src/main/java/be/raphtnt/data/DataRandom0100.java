package be.raphtnt.data;

import java.util.Random;

public class DataRandom extends Data{

    @Override
    public String name() {
        return "Nombre aléatoire entre 0 et 100";
    }

    @Override
    public Object process() {
        Random random = new Random();
        return random.nextInt(101);
    }

}