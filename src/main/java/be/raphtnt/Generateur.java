package be.raphtnt;

import be.raphtnt.data.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generateur {

    public static ArrayList<Data> dataList = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private HashMap<String, Data> tempData = new HashMap<>();

    private int nbLine = 50;
    public static int idIncrement = 1;
    private int currentTable = 0;
    /* Path ou le fichier .sql va être générer */
    private String path = "C:\\test\\";
    /* Path de lecture du fichier .sql */
    private String pathReadSQL = "C:\\Users\\raph\\Downloads\\script.sql";
    /* Nom du fichier qui va être généré */
    private String nameFile = "alper";

    public static boolean debug = true;

    private void initData() {
        new DataIncrement().execute();
        new DataPrenom().execute();
        new DataNom().execute();
        new DataEmail().execute();
        new DataRandom010().execute();
        new DataRandom050().execute();
        new DataRandom0100().execute();
        new DataRandom01000().execute();
        new DataVille().execute();
        new DataPays().execute();
        new DataPhone().execute();
        new DataPhoneString().execute();
        new DataVehicule().execute();
        new DataLicense().execute();
        new DataDateRandomString().execute();
    }

    public void init() {

        if(!debug) {
            path = input("Ou voulez-vous stocké votre fichier .sql ?");
            if(path.charAt(path.length()-1) != '/' || path.charAt(path.length()-1) != '\\') {
                path = path + "/";
            }
        }

        if(!debug) {
            nameFile = input("Nom du fichier (sans extension) : ");
        }

        path = path + nameFile + ".sql";
        createFile(path);

        initData();

        if(!debug) {
            pathReadSQL = input("Path du fichier .sql a lire : ");
        }
        readTableInsertModal(pathReadSQL);
//        insertTable();

        while (!tables.isEmpty()) {
            String response = input("Voulez-vous continué ? (Y/N)");
            if(response.equalsIgnoreCase("Y")) {
                insertTable();
            } else {
                System.exit(0);
            }
        }

    }

    private void createFile(String filePathName) {
        try {
            Path filePath = Paths.get(filePathName);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println(filePath + " a été créé !");
            } else {
                System.out.println(filePath + " existe déjà.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAppendFile(String content, String filePathName) {
        Path filePath = Paths.get(filePathName);

        try {
            if (Files.exists(filePath)) {
                Files.writeString(filePath, content, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } else {
                System.out.println(filePath + " n'existe pas.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void insertTable() {
        Table table = selectTable();
        System.out.println("Insertion des données pour la table : " + table.getName());

        nbLine = Integer.parseInt(input("Combien de données voulez-vous pour cette table ?"));

        /*        String dataName = data.name();
        String dataValue = data.process().toString();
        String insertQuery = "INSERT INTO " + table.getName() + "(" + colonne + ") VALUES (" + dataValue + ")";
        System.out.println(insertQuery); IA */

        table.getColumns().forEach(this::selectData);

        StringBuilder stringBuilder = new StringBuilder();
        for (String column : table.getColumns()) {
            stringBuilder.append(column + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder);

        StringBuilder stringBuilder1 = new StringBuilder();


        for (int i = 0; i < nbLine; i++) {
            stringBuilder1.delete(0, stringBuilder1.length());
            for (String column : table.getColumns()) {
                Data data = tempData.get(column);
                Object dataValue = data.process();
                System.out.println(data.name() + " | " + data.firstProcess);
                if(!(data instanceof NotStr)) {
                    stringBuilder1.append("'" + dataValue + "',");
                } else {
                    stringBuilder1.append(dataValue + ",");
                }
            }

            stringBuilder1.deleteCharAt(stringBuilder1.length() - 1);
            String insertQuery = "INSERT INTO " + table.getName() + "(" + stringBuilder + ") VALUES (" + stringBuilder1 + ");";
            System.out.println(insertQuery);
            readAppendFile(insertQuery + "\n", path);
        }

        dataList.forEach(l -> {
            l.firstProcess = true;
        });

        tables.remove(currentTable);
    }


    private void selectData(String colonne) {
        idIncrement = 1;

        int i = 0;
        for(Data data : dataList) {
            System.out.println(i + ". " + data.name());
            i++;
        }

        int result = Integer.parseInt(input("Quelle type de donnée voulez-vous inserez pour la colonne : " + colonne));
        Data data = dataList.get(result);
        tempData.put(colonne, data);

    }

    private Table selectTable() {
        int i = 0;
        for (Table table : tables) {
            System.out.println(i + ". " + table.getName() + " | " + table.getColumns());
            i++;
        }

        System.out.println();
        int tableIndex = Integer.parseInt(input("Sélectionner votre table : "));
        Table selectedTable = tables.get(tableIndex);
        currentTable = tableIndex;
        return selectedTable;

    }

    public static String input(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // C:\Users\raph\Downloads\gcs.sql
    private void readTableInsertModal(String pathReadSQL) {
        try {
//            ArrayList<String> columnList = new ArrayList<>();
            String filePath = pathReadSQL;
            String sqlString = new String(Files.readAllBytes(Paths.get(filePath)));
            Pattern pattern = Pattern.compile("CREATE\\s+TABLE\\s+(\\w+)\\s*\\((.*?)\\)\\s*(?:ENGINE=InnoDB)?\\s*(?:;|$)", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(sqlString);

            while (matcher.find()) {
                String tableName = matcher.group(1);
                String[] columns = matcher.group(2).trim().split(",\\s*(?=(?:[^']*'[^']*')*[^']*$)");
                Table table = new Table(tableName);
                for (String column : columns) {
                    String columnName = column.trim().split("\\s+")[0];
                    if (!columnName.startsWith("CONSTRAINT") && !columnName.contains(")") && !columnName.contains("0)") && !columnName.contains("AUTO_INCREMENT")) {
                        table.addColumn(columnName);
                    }
                }
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
