package be.raphtnt.data;

public class DataPays extends Data{
    @Override
    public String name() {
        return "Pays";
    }

    @Override
    public Object process() {
        return randomDataFromFile("pays.json");
    }

}
