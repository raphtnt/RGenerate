package be.raphtnt.data;

public class DataPrenom extends Data{
    @Override
    public String name() {
        return "Prénom";
    }

    @Override
    public Object process() {
        return randomDataFromFile("prenom.json");
    }

}
