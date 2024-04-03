package be.raphtnt.data;

public class DataNom extends Data{
    @Override
    public String name() {
        return "Nom";
    }

    @Override
    public Object process() {
        return randomDataFromFile("nom.json");
    }

}
