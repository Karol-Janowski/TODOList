package Utils;

public class PrintingUtils {
    public static void greetings() {
        System.out.println();
        System.out.println("==============WELCOME TO TODO LIST PROGRAM =======================");
        System.out.println();
    }

    public static void enterCommand() {
        System.out.println("ENTER COMMAND:");
        System.out.println("1. CREATE;NAME=task_name;DESCRIPTION=your_description;DEADLINE=yyyy-mm-dd hh:mm:ss;PRIORITY=0-10");
        System.out.println("2. UPDATE;NAME=task_name;DESCRIPTION=your_description;DEADLINE=yyyy-mm-dd hh:mm:ss;PRIORITY=0-10");
        System.out.println("3. READ;NAME=task_name");
        System.out.println("4. READ ALL;");
        System.out.println("5. DELETE;NAME=task_name");
        System.out.println("6. EXIT");
        System.out.println();
    }

    public static void enterProperCommand() {
        System.out.println("ENTER PROPER COMMAND:");
        System.out.println("1. CREATE;NAME=task_name;DESCRIPTION=your_description;DEADLINE=yyyy-mm-dd hh:mm:ss;PRIORITY=0-10");
        System.out.println("2. UPDATE;NAME=task_name;DESCRIPTION=your_description;DEADLINE=yyyy-mm-dd hh:mm:ss;PRIORITY=0-10");
        System.out.println("3. READ;NAME=task_name");
        System.out.println("4. READ ALL;");
        System.out.println("5. DELETE;NAME=task_name");
        System.out.println("6. EXIT");
        System.out.println();
    }
}
