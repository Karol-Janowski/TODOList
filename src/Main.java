import java.util.Scanner;

import static Utils.DataBaseUtils.*;
import static Utils.PrintingUtils.*;
import static Utils.CommandRegexesUtils.*;
import static Utils.getDataFromCommandUtils.*;

public class Main {
    public static void main(String[] args) {

        if (!checkDBExists("todolist")) {
            createTableTODOList();
        }

        greetings();
        enterCommand();

        boolean programStarter = true;
        Scanner scanner = new Scanner(System.in);
        String command;

        while (programStarter) {
            System.out.print("todoList->:");
            command = scanner.nextLine();

            if (exitRegex(command)) {
                scanner.close();
                programStarter = false;
            } else if (createRegex(command)) {
                getDataFromCommandAndAndInsertToDataBase(command);
                System.out.println();
                readAllfromDatabase();
                System.out.println();
            } else if (updateRegex(command)) {
                getDataFromCommandAndUpdateToDataBase(command);
                System.out.println();
                readAllfromDatabase();
                System.out.println();
            } else if (readByNameRegex(command)) {
                getDataFromCommandAndPrintByName(command);
            } else if (readAllRegex(command)){
                readAllfromDatabase();
            } else if (deleteByNameRegex(command)) {
                getDataFromCommandAndDeleteFromDB(command);
            } else if (readAndSortRegex(command)) {
                getDataFromCommandAndPrintSorted(command);
            } else if (groupedByRegex(command)){
                printGroupedByDateDatafromDB();
            } else {
                enterProperCommand();
            }
        }
    }
}

