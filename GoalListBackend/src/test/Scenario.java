package test;

import javasrc.DataValidationException;
import javasrc.Goal;
import javasrc.User;
import javasrc.UserInterface;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class Scenario {
    User scenarioTestUser;
    List<Goal> scenarioTestList;

    @BeforeClass
    public void setUp() throws Exception{
        UserInterface.init();

        //Given I have a goal list
        scenarioTestUser = UserInterface.getUserInfo();
        scenarioTestList = UserInterface.getGoals();

    }

    @Test
    public void simpleScenarioTest() throws DataValidationException, SQLException {

        //When I add goal with some valid (Name, Desc) to a list
        int oldListSize = scenarioTestList.size();
        String goalTestName = "Study java";
        String goalTestDesc = "Learn more about java concurrency";
        UserInterface.addGoal(goalTestName, goalTestDesc);

        //Then goal is added and is not reached
        scenarioTestList = UserInterface.getGoals();
        int newListSize = scenarioTestList.size();
        System.out.println("Test that goal was added...");
        Assert.assertEquals(oldListSize + 1, newListSize, "Error: goal list size did not change as expected\n");
        Goal addedGoal = scenarioTestList.get(newListSize - 1);

        Assert.assertEquals(addedGoal.getGoalName(), goalTestName, "Error: goal name does not match input name\n");
        Assert.assertEquals(addedGoal.getGoalDescription(), goalTestDesc, "Error: goal description does not match input\n");
        Assert.assertEquals(addedGoal.getIsReachedFlag(), false, "Error: IsReached flag is incorrect");

        //When I change goal description
        String newGoalName = "Study python";
        String newGoalDesc = "Python is cool too";
        UserInterface.editGoalName(addedGoal, newGoalName);
        UserInterface.editGoalDescription(addedGoal, newGoalDesc);

        //Then it is changed
        scenarioTestList = UserInterface.getGoals();
        Goal editedGoal = scenarioTestList.get(newListSize - 1);
        System.out.println("Test that goal was edited...");
        Assert.assertEquals(editedGoal.getGoalName(), newGoalName, "Error: goal name was edited wrong");
        Assert.assertEquals(editedGoal.getGoalDescription(), newGoalDesc, "Error: goal description was edited wrong");

        //When I toggle IsReached flag
        UserInterface.editGoalReachedFlag(editedGoal, true);

        //Then Flag changes
        scenarioTestList = UserInterface.getGoals();
        editedGoal = scenarioTestList.get(newListSize - 1);
        Assert.assertEquals(editedGoal.getIsReachedFlag(), true, "Error: goal IsReached flag has not changed");

    }

    @AfterClass
    public void tearDown() {
        UserInterface.close();
    }
}


