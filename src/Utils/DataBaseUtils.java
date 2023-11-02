package Utils;

import java.sql.*;

public class DataBaseUtils {

    static private String dataBaseURL = "jdbc:postgresql://localhost:5432/todolist";
    static private String username = "postgres";
    static private String password = "";

    public static boolean checkDBExists(String dbName) {

        try {

            Connection connection = DriverManager.getConnection(dataBaseURL, username, password);

            ResultSet resultSet = connection.getMetaData().getCatalogs();

            while (resultSet.next()) {

                String databaseName = resultSet.getString(1);
                if (databaseName.equals(dbName)) {
                    return true;
                }
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createTableTODOList() {

        String createTable = "CREATE TABLE IF NOT EXISTS TODOLIST (" +
                "NAME VARCHAR(32) NOT NULL," +
                "DESCRIPTION TEXT," +
                "DEADLINE TIMESTAMP WITH TIME ZONE NOT NULL," +
                "PRIORITY INT CHECK (PRIORITY IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) NOT NULL," +
                "STATUS STATUS DEFAULT 'TODO' NOT NULL " +
                "UNIQUE (NAME)" +
                ")";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(createTable);
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoDatabase(
            final String name,
            final String timestamp,
            final String priority) {

        String query = "INSERT INTO TODOLIST (NAME, DEADLINE, PRIORITY) VALUES (" +
                "'" + name + "'," +
                "'" + timestamp + "'," +
                "'" + priority + "');";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoDatabase(
            final String name,
            final String description,
            final String timestamp,
            final String priority) {

        String query = "INSERT INTO TODOLIST (NAME, DESCRIPTION, DEADLINE, PRIORITY) VALUES (" +
                "'" + name + "'," +
                "'" + description + "'," +
                "'" + timestamp + "'," +
                "'" + priority + "');";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.executeUpdate();
            System.out.println("Insert executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDataBase(
            String name,
            String description,
            Timestamp timestamp,
            int priority) {

        String query = "UPDATE TODOLIST SET DESCRIPTION = ?, DEADLINE = ?, PRIORITY = ? WHERE NAME = ?";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, description);
            statement.setTimestamp(2, timestamp);
            statement.setInt(3, priority);
            statement.setString(4, name);

            statement.executeUpdate();
            System.out.println("Update executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void readAllFromDataBaseAndSort(String orderBy) {
        String query = "SELECT * FROM TODOLIST ORDER BY " + orderBy + " DESC;";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + ", ");
                System.out.print(resultSet.getString(5));
                System.out.println();
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readByNameFromDataBase(String name) {
        String query = "SELECT * FROM TODOLIST WHERE NAME = ?;";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + ", ");
                System.out.print(resultSet.getString(5));
                System.out.println();
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readAllfromDatabase() {

        String query = "SELECT * FROM TODOLIST;";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + ", ");
                System.out.print(resultSet.getString(5));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readGroupedByFromDB() {
        String query = "SELECT NAME, DESCRIPTION, DATE(DEADLINE), PRIORITY FROM TODOLIST " +
                "GROUP BY NAME, DESCRIPTION, DATE(DEADLINE), PRIORITY " +
                "ORDER BY DATE(DEADLINE)";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + ", ");
                System.out.print(resultSet.getString(5));
                System.out.println();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeStatusToCompleted(String name) {
        String query = "UPDATE TODOLIST SET STATUS = 'COMPLETED' WHERE NAME=?";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, name);

            statement.executeUpdate();
            System.out.println("Status changed to completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void deleteByNameFromDataBase(String name) {

        String fetchQuery = "SELECT * FROM TODOLIST WHERE NAME = ?";
        String deleteQuery = "DELETE FROM TODOLIST WHERE NAME = ?";

        try (
                Connection connection = DriverManager.getConnection(dataBaseURL, username, password);
                PreparedStatement fetchStatement = connection.prepareStatement(fetchQuery);
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        ) {
            fetchStatement.setString(1, name);
            deleteStatement.setString(1, name);

            ResultSet resultSet = fetchStatement.executeQuery();
            deleteStatement.executeUpdate();

            while (resultSet.next()) {
                System.out.print("deleted: ");
                System.out.print(resultSet.getString(1) + ", ");
                System.out.print(resultSet.getString(2) + ", ");
                System.out.print(resultSet.getString(3) + ", ");
                System.out.print(resultSet.getString(4) + ", ");
                System.out.print(resultSet.getString(5));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
