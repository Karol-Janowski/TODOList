package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRegexesUtils {

    public static boolean createRegex(String command) {
        Pattern createPattern = Pattern.compile(
                "^CREATE;NAME=.*;DESCRIPTION=.*;DEADLINE=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2};PRIORITY=[0-9]{1}[0]?$");
        Matcher createMatcher = createPattern.matcher(command);
        return createMatcher.find();
    }

    public static boolean updateRegex(String command) {
        Pattern updatePattern = Pattern.compile(
                "^UPDATE;NAME=.*;DESCRIPTION=.*;DEADLINE=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2};PRIORITY=[0-9]{1}[0]?$");
        Matcher updateMatcher = updatePattern.matcher(command);
        return updateMatcher.find();
    }

    public static boolean readByNameRegex(String command) {
        Pattern readByNamePattern = Pattern.compile("^READ;NAME=.*$");
        Matcher readByNameMatcher = readByNamePattern.matcher(command);
        return readByNameMatcher.find();
    }

    public static boolean readAllRegex(String command) {
        Pattern readAllPattern = Pattern.compile("^READ ALL;$");
        Matcher readAllMatcher = readAllPattern.matcher(command);
        return readAllMatcher.find();
    }

    public static boolean readAndSortRegex(String command) {
        Pattern readAndSortPattern = Pattern.compile("^READ ALL;SORT=.*,DESC$");
        Matcher readAndSortMatcher = readAndSortPattern.matcher(command);
        return readAndSortMatcher.find();
    }

    public static boolean groupedByRegex(String command) {
        Pattern groupedByPattern = Pattern.compile("^READ GROUPED;$");
        Matcher groupedByMatcher = groupedByPattern.matcher(command);
        return groupedByMatcher.find();
    }

    public static boolean deleteByNameRegex(String command) {
        Pattern deletePattern = Pattern.compile("^DELETE;NAME=.*$");
        Matcher deleteMatcher = deletePattern.matcher(command);
        return deleteMatcher.find();
    }

    public static boolean exitRegex (String command) {
        Pattern exitPattern = Pattern.compile("^\\s*EXIT\\s*$");
        Matcher exitMatcher = exitPattern.matcher(command);
        return exitMatcher.find();
    }
}
