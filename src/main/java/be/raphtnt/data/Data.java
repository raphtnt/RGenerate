package be.raphtnt.data;

import be.raphtnt.Generateur;
import be.raphtnt.Main;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public abstract class Data {

    public boolean firstProcess = true;

    public abstract String name();

    public abstract Object process();

    /**
     * Generates a random data from a specified JSON file.
     *
     * @param nameJson the name of the JSON file from ressource file
     * @return a random data from the file
     */
    public String randomDataFromFile(String nameJson) {
        String modifiedContents = readJsonFile("/" + nameJson).replace("[", "").replace("]", "").replace("\"", "").trim();
        String[] tab = modifiedContents.split(",");
        Random random = new Random();
        return tab[random.nextInt(tab.length)].trim();
    }

    public void execute() {
        Generateur.dataList.add(this);
    }


    public static String readJsonFile(String filepath) {
        String content = "";

        try (InputStream is = Main.class.getResourceAsStream(filepath)) {
            if (is != null) {
                byte[] bytes = is.readAllBytes();
                content = new String(bytes, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

}
