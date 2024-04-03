package be.raphtnt.data;

import be.raphtnt.Generateur;
import be.raphtnt.Main;

public class DataIncrement extends Data implements NotStr
{
    @Override
    public String name() {
        return "Increment";
    }

    @Override
    public Object process() {
        this.firstProcess = true;
        return Generateur.idIncrement++;
    }

}
