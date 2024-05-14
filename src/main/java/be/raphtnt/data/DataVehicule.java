package be.raphtnt.data;

public class DataVehicule extends Data{
    @Override
    public String name() {
        return "Pays";
    }

    @Override
    public Object process() {
        return randomDataFromFile("vehicule.json");
    }

}
