package be.raphtnt.data;

public class DataLicense extends Data{
    @Override
    public String name() {
        return "License";
    }

    @Override
    public Object process() {
        return randomDataFromFile("license.json");
    }

}
