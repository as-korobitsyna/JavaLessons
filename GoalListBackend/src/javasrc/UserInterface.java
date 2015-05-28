package javasrc;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserInterface {

    private static User user;

    public static void init() throws SQLException {
        Database.openDatabase("GoalList.db");
        user = Database.getUser();
    }

    public static void showUserInfo() {
        System.out.println(user.toString());
    }

    public static User getUserInfo() {
        User retUser;

        retUser = user;
        if(user.getNickName() == null) retUser.setNickName("");
        if(user.getPhoneNumber() == null) retUser.setPhoneNumber("");
        return retUser;

    }

    public static void editUserNickname(String newNickname) throws SQLException, DataValidationException {
        if (newNickname!= null) {
            user.setNickName(newNickname);
            Database.updateUser(user);
        }
        else throw new DataValidationException("Can not pass null to this field");
    }

    public static void editUserPhoneNumber(String newNumber) throws SQLException, DataValidationException {
        if (newNumber!= null) {
            user.setPhoneNumber(newNumber);
        Database.updateUser(user);
        }
        else throw new DataValidationException("Can not pass null to this field");
    }

    public static void showGoals() throws SQLException, DataValidationException{
        ArrayList<Goal> list = user.getGoals();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public static ArrayList<Goal> getGoals() throws DataValidationException, SQLException {return user.getGoals();}

    public static void editGoalName(Goal goal, String newName) throws SQLException, DataValidationException {
        if (newName!= null) {
            goal.setGoalName(newName);
            Database.updateGoal(goal);
        }
        else throw new DataValidationException("Can not pass null to this field");

    }

    public static void editGoalDescription(Goal goal, String newDescription) throws SQLException, DataValidationException {
        if (newDescription!= null) {
            goal.setGoalDescription(newDescription);
            Database.updateGoal(goal);
        }
        else throw new DataValidationException("Can not pass null to this field");
    }

    public static void editGoalReachedFlag (Goal goal, boolean reached) throws SQLException {
        goal.setIsReachedFlag(reached);
        Database.updateGoal(goal);
    }

    public static void addGoal(String goalName, String goalDescription) throws DataValidationException, SQLException {

        int tempID = -1;
        Goal goal = new Goal(tempID, user);
        goal.setGoalName(goalName);
        goal.setGoalDescription(goalDescription);
        Database.createGoal(goal);

    }

    public static void close() {
        Database.closeConnection();
    }

}