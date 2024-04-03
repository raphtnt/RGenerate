package be.raphtnt.data;

import be.raphtnt.Generateur;
import be.raphtnt.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDateRandomString extends Data implements NotStr {
    @Override
    public String name() {
        return "Date (String)";
    }

    public static String format = "";
    public static int returnValue = 0;

    @Override
    public Object process() {
        if(firstProcess) {
            firstProcess = false;

            System.out.println("0. String");
            System.out.println("1. to_date(date, format)");
            System.out.println("2. DATE_FORMAT(date, format)");
            returnValue = Integer.parseInt(Generateur.input("Quelle type de valeur veux tu retournée ? "));

            if(returnValue == 2) {
                System.out.println("Doc : https://sql.sh/fonctions/date_format");
                System.out.println("%d : Jour du mois en chiffres (01..31)");
                System.out.println("%m : Mois en chiffres (01..12)");
                System.out.println("%Y : Année sur quatre chiffres");
/*                System.out.println("%H : Heure (00..23)");
                System.out.println("%i : Minutes (00..59)");
                System.out.println("%s : Secondes (00..59)");*/
            } else {
                System.out.println("DOC : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html#patterns");
                System.out.println("dd = jour");
                System.out.println("MM = mois");
                System.out.println("yyyy = année");
                System.out.println("Exemple de format : dd-MM-yyyy | dd/MM/yyyy");
            }

            format = Generateur.input("Ecrit ton format : ");


        }
        LocalDate minDate = LocalDate.of(2000, 1, 1);
        LocalDate maxDate = LocalDate.now();
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay); // Format : yyyy-MM-dd
        DateTimeFormatter formatter;
        String formattedRandomDate;
        if(returnValue == 2) {
            Pattern pattern = Pattern.compile("%[dmYHis]");
            Matcher matcher = pattern.matcher(format);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String match = matcher.group();
                switch (match) {
                    case "%d":
                        matcher.appendReplacement(sb, String.format("%02d", randomDate.getDayOfMonth()));
                        break;
                    case "%m":
                        matcher.appendReplacement(sb, String.format("%02d", randomDate.getMonthValue()));
                        break;
                    case "%Y":
                        matcher.appendReplacement(sb, String.valueOf(randomDate.getYear()));
                        break;
/*                    case "%H":
                        matcher.appendReplacement(sb, String.valueOf(randomDate.getHour()));
                        break;
                    case "%i":
                        matcher.appendReplacement(sb, String.valueOf(randomDate.getMinute()));
                        break;
                    case "%s":
                        matcher.appendReplacement(sb, String.valueOf(randomDate.getSecond()));
                        break;*/
                }
            }
            matcher.appendTail(sb);
            formattedRandomDate = sb.toString();
        } else {
            formatter = DateTimeFormatter.ofPattern(format); // dd-MM-yyyy
            formattedRandomDate = randomDate.format(formatter);
        }

        String result = "";
        switch (returnValue) {
            case 1:
                result = "to_date('" + formattedRandomDate + "', '" + format + "')";
                break;
            case 2:
                result = "DATE_FORMAT('" + formattedRandomDate + "', '" + format + "')";
                break;
            case 0:
            default:
                result = "'" + formattedRandomDate + "'";
                break;

        }

        return result;
    }

}
