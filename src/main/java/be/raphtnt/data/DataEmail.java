package be.raphtnt.data;

public class DataEmail extends Data{
    @Override
    public String name() {
        return "Nom";
    }

    @Override
    public Object process() {
        String prenom = randomDataFromFile("prenom.json");
        String nom = randomDataFromFile("nom.json");
        String extension = randomDataFromFile("extension.json");
        String email = prenom + "@" + nom + "." + extension;
        return email;
    }

}
