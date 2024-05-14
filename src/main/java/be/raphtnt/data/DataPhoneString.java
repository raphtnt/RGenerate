package be.raphtnt.data;

import java.util.Random;

public class DataPhoneString extends Data{
    @Override
    public String name() {
        return "Numéro de téléphone (String)";
    }

    @Override
    public Object process() {
        String num = "04";
        int randomNumber = randomInt(11111111, 99999999);
        num = num + randomNumber;
        return num;
    }

    private int randomInt(int Min, int Max)  {
        return (int) (Math.random()*(Max-Min))+Min;
    }

}
