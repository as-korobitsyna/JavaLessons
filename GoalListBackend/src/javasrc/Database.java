package javasrc;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection c;
    private static final String usersTable = "USERS";
    private static final String goalsTable = "GOALS";

    public static void openDatabase(String DB) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public static void closeConnection() {
        try {
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static User getUser() throws SQLException {
        PreparedStatement statement;

        String selectString = "SELECT * FROM "+ usersTable + ";";
        statement = c.prepareStatement(selectString);
        ResultSet rs = statement.executeQuery();

        if(!rs.next()) {
            return createUser("Name", "Surname");
        } else {
            long id = rs.getLong("USERID");
            String fn = rs.getString("FIRSTNAME");
            String ln = rs.getString("LASTNAME");
            return new User(id, fn, ln);
        }
    }

    public static User createUser(String firstName, String lastName) throws SQLException {
        PreparedStatement insertUser;

        String insertString = "INSERT INTO "+ usersTable + " (FIRSTNAME,LASTNAME) " + "VALUES (?,?);";
        insertUser = c.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);

        insertUser.setString(1, firstName);
        insertUser.setString(2, lastName);

        int affectedRows = insertUser.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        long id;

        try (ResultSet generatedKeys = insertUser.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        User user = new User(id, firstName, lastName);

        return user;
    }

    public static void updateUser(User user) throws SQLException{
        PreparedStatement updateUser;

        String updateString = "UPDATE "+ usersTable + " set NICKNAME = ?, PHONENUMBER = ? WHERE USERID = ?;";
        updateUser = c.prepareStatement(updateString);

        updateUser.setString(1, user.getNickName());
        updateUser.setString(2, user.getPhoneNumber());
        updateUser.setLong(3, user.getUserID());

        if(updateUser.executeUpdate() != 1) throw new SQLException("Update failed");

    }

    public static Goal createGoal(Goal goal) throws SQLException, DataValidationException {
        PreparedStatement insertGoal;

        String insertString = "INSERT INTO "+ goalsTable + " (USERID, NAME, DESCRIPTION) " + "VALUES (?, ?, ?);";
        insertGoal = c.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);

        insertGoal.setLong(1, goal.getGoalUser().getUserID());
        insertGoal.setString(2, goal.getGoalName());
        insertGoal.setString(3, goal.getGoalDescription());

        int affectedRows = insertGoal.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating goal failed, no rows affected.");
        }

        long id;

        try (ResultSet generatedKeys = insertGoal.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating goal failed, no ID obtained.");
            }
        }

        Goal g = new Goal(id, goal.getGoalUser());
        g.setGoalName(goal.getGoalName());
        g.setGoalDescription(goal.getGoalDescription());
        return g;

    }

    public static void updateGoal(Goal goal) throws SQLException{
        PreparedStatement updateGoal;

        String updateString = "UPDATE "+ goalsTable + " set NAME = ?, DESCRIPTION = ?, REACHED = ? WHERE GOALID = ?;";
        updateGoal = c.prepareStatement(updateString);

        updateGoal.setString(1, goal.getGoalName());
        updateGoal.setString(2, goal.getGoalDescription());
        updateGoal.setBoolean(3, goal.getIsReachedFlag());
        updateGoal.setLong(4, goal.getGoalID());

        if(updateGoal.executeUpdate() != 1) throw new SQLException("Update failed");


    }

    public static ArrayList<Goal> getUserGoals(User user) throws SQLException, DataValidationException {
        ArrayList<Goal> returnList = new ArrayList<>();

        PreparedStatement statement;

        String selectString = "SELECT * FROM "+ goalsTable + " WHERE USERID = ?;";
        statement = c.prepareStatement(selectString);
        statement.setLong(1, user.getUserID());

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            long id = rs.getLong("GOALID");
            Goal t = new Goal(id, user);

            t.setGoalName(rs.getString("NAME"));
            t.setGoalDescription(rs.getString("DESCRIPTION"));
            t.setIsReachedFlag(rs.getBoolean("REACHED"));

            returnList.add(t);
        }

        return returnList;
    }
}
