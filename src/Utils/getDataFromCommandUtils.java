package Utils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.DataBaseUtils.*;

public class getDataFromCommandUtils {

    public static void getDataFromCommandAndDeleteFromDB(String command) {
        List<String> split = Arrays.stream(command.split(";"))
                .filter(e -> e.contains("="))
                .map(e -> e.replaceAll(".*=", ""))
                .collect(Collectors.toList());

        String name = split.get(0);

        deleteByNameFromDataBase(name);
    }

    public static void getDataFromCommandAndPrintByName(String command) {
        List<String> split = Arrays.stream(command.split(";"))
                .filter(e -> e.contains("="))
                .map(e -> e.replaceAll(".*=", ""))
                .collect(Collectors.toList());

        String name = split.get(0);

        readByNameFromDataBase(name);
    }

    public static void getDataFromCommandAndPrintSorted(String command) {
        List<String> split = Arrays.stream(command.split(";"))
                .filter(e -> e.contains("="))
                .map(e -> e.replaceAll(".*=", ""))
                .collect(Collectors.toList());

        String sortBy = split.get(0);

        readAllFromDataBaseAndSort(sortBy);
    }

    public static void getDataFromCommandAndAndInsertToDataBase(String command) {
        List<String> split = Arrays.stream(command.split(";"))
                .filter(e -> e.contains("="))
                .map(e -> e.replaceAll(".*=", ""))
                .collect(Collectors.toList());

        String name = split.get(0);
        String description = split.get(1);
        String timestap = split.get(2);
        String priority = split.get(3);

        insertIntoDatabase(name, description, timestap, priority);
    }

    public static void getDataFromCommandAndUpdateToDataBase(String command) {
        List<String> split = Arrays.stream(command.split(";"))
                .filter(e -> e.contains("="))
                .map(e -> e.replaceAll(".*=", ""))
                .collect(Collectors.toList());

        String name = split.get(0);
        String description = split.get(1);
        String timestap = split.get(2);
        int priority = Integer.parseInt(split.get(3));

        updateDataBase(name, description, Timestamp.valueOf(timestap), priority);
    }
}
