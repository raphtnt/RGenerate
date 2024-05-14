package be.raphtnt.data;

public class DataVehicule extends Data{
    @Override
    public String name() {
        return "Vehicules";
    }

    @Override
    public Object process() {
        return randomDataFromFile("vehicule.json");
    }

}
