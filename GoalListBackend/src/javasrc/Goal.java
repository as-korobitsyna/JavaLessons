package javasrc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Goal {

    private long goalID;
    private User goalUser;
    private String goalName;
    private String goalDescription;
    private boolean isReachedFlag;

    public Goal(long ID, User user) {
        goalID = ID;
        goalUser = user;
    }

    public User getGoalUser() {
        return this.goalUser;
    }

    public long getGoalID() {
        return this.goalID;
    }

    public String getGoalName() {
        return this.goalName;
    }

    public String getGoalDescription() {
        return this.goalDescription;
    }

    public boolean getIsReachedFlag() {
        return this.isReachedFlag;
    }

    public void setGoalDescription(String goalDescription) throws DataValidationException{
        if(goalDescription.length() < 10 || goalDescription.length() > 50 )
            throw new DataValidationException("Goal description length error");
        this.goalDescription = goalDescription;
    }

    public void setIsReachedFlag(boolean isReachedFlag) {
         this.isReachedFlag = isReachedFlag;
    }

    public void setGoalName(String goalName) throws DataValidationException{
        if(goalName.length() < 6 || goalName.length() > 20)
            throw new DataValidationException("Goal name length error");
        String regex = "[0-9!@#\\$%\\^\\&\\*\\(\\)\\{\\}\\\"\\|\\?><:;']";
        Matcher matcher = Pattern.compile(regex).matcher(goalName);
        if (matcher.find())
        {
            throw new DataValidationException("Goal name invalid symbol error");
        }
        this.goalName = goalName;
    }

    public String toString() {
        String reached = this.getIsReachedFlag() ? "Yes" : "No";

        return("Goal ID: " + this.getGoalID() + "\n"
                + "Name: " + this.getGoalName() + "\n"
                + "Description: " + this.getGoalDescription() + "\n"
                + "Reached: " + reached + "\n\n");
    }


}
