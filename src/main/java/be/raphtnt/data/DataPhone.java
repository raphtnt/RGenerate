package be.raphtnt.data;

import java.util.Random;

public class DataPhone extends Data implements NotStr{
    @Override
    public String name() {
        return "Numéro de téléphone";
    }

    @Override
    public Object process() {
        String num = "04";
        int randomNumber = randomInt(11111111, 99999999);
        num = num + randomNumber;
        return Integer.parseInt(num);
    }

    private int randomInt(int Min, int Max)  {
        return (int) (Math.random()*(Max-Min))+Min;
    }

}
