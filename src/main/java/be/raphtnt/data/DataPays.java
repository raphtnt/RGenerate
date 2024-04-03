package be.raphtnt.data;

public class DataVille extends Data{
    @Override
    public String name() {
        return "Ville";
    }

    @Override
    public Object process() {
        return randomDataFromFile("ville.json");
    }
}
