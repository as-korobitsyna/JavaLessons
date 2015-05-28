package test;

import javasrc.*;

import org.testng.annotations.*;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

//This test contains simple test methods for main UserInterface methods

public class UnitTests {

    public User testUser;
    public ArrayList<Goal> testGoals;
    public Goal testGoal;
    int randomGoalID;



    @BeforeMethod
    public void setUp() throws Exception {
        //Not sure if we should use methods from class under test to setup and teardown test
        UserInterface.init();

        testUser = UserInterface.getUserInfo();
        testGoals = UserInterface.getGoals();
        Random rnd = new Random();

        if(testGoals.size() > 1) {
            randomGoalID = rnd.nextInt(testGoals.size() - 1);
            testGoal = testGoals.get(randomGoalID);
        }
        else {
            UserInterface.addGoal("ValidName", "ValidDesciption");
            randomGoalID = 0;
            reloadTestGoal();
        }
    }

    @Test
    public void editNicknameValidData() throws SQLException, DataValidationException {
        String oldNickName = testUser.getNickName();
        UserInterface.editUserNickname(oldNickName + "nick");

        String newNickName = UserInterface.getUserInfo().getNickName();

        Assert.assertEquals(oldNickName + "nick", newNickName, "Editing user nickname error: edition with valid data did not pass");
    }

    @Test
    public void editNickNameNull() throws SQLException, DataValidationException {
        UserInterface.editUserNickname("ValidNickname");
        String newNickName = null;
        try { UserInterface.editUserNickname(newNickName);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally {
            Assert.assertNotNull(UserInterface.getUserInfo().getNickName(), "Editing user nickname error: can edit to null");
        }
    }

    @Test
    public void editPhoneNumberValidData() throws SQLException, DataValidationException {
        String oldPhone = testUser.getPhoneNumber();
        UserInterface.editUserPhoneNumber(oldPhone + "12345");

        String newPhone = UserInterface.getUserInfo().getPhoneNumber();

        Assert.assertEquals(oldPhone + "12345", newPhone, "Editing user phone error: edition with valid data did not pass");
    }

    @Test
    public void editPhoneNumberNull() throws SQLException, DataValidationException {
        UserInterface.editUserPhoneNumber("123-123");
        String newPhone = null;
        try {
            UserInterface.editUserPhoneNumber(newPhone);
        }
        catch(Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally {
            Assert.assertNotNull(UserInterface.getUserInfo().getPhoneNumber(), "Editing user phone error: can edit to null");
        }

    }

    @Test
    public void editGoalNameValid() throws DataValidationException, SQLException {
        //it shouldn’t contain numbers and special characters
        //it shouldn’t be less than 6 characters and more than 20
        String oldGoaName = testGoal.getGoalName();
        String validGoalName = "ValidName";
        if(!Objects.equals(oldGoaName, validGoalName))
            UserInterface.editGoalName(testGoal, validGoalName);
        else {
            validGoalName = validGoalName.replace(validGoalName.charAt(1), validGoalName.charAt(validGoalName.length() - 1));
            UserInterface.editGoalName(testGoal, validGoalName);
        }

        reloadTestGoal();
        Assert.assertEquals(testGoal.getGoalName(), validGoalName, "Edit Goal Name error: can not edit with valid data");
    }

    @Test
    public void editGoalNameNull() throws DataValidationException, SQLException {

        String nullName = null;
        try {UserInterface.editGoalName(testGoal, nullName);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally{
            reloadTestGoal();
            Assert.assertNotNull(testGoal.getGoalName(), "Edit Goal Name error: can edit to null");
        }

    }

    @Test
    public void editGoalNameInvalidSymbols() throws DataValidationException, SQLException {
        String[] invSymbolsName = { "GoalWith123", "GoalWith$!&"};
        for(int i = 0; i < invSymbolsName.length; i++) {
            try {UserInterface.editGoalName(testGoal, invSymbolsName[i]);}
            catch (Exception e) {
                System.out.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Exeption was thrown and it's OK!\n");
            }
            finally{
                reloadTestGoal();
                Assert.assertFalse((testGoal.getGoalName().equals(invSymbolsName[i])),
                        "Edit Goal Name error: can edit with name containing numbers or special symbols");
            }
        }

    }

    @Test
    public void editGoalNameInvalidShort() throws DataValidationException, SQLException {

        String shortName = "Name";
        try {
            UserInterface.editGoalName(testGoal, shortName);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally{
            reloadTestGoal();
            Assert.assertFalse((testGoal.getGoalName().equals(shortName)),
                    "Edit Goal Name error: can edit with name less than 6 symbols");
        }

    }

    @Test
    public void editGoalNameInvalidLong() throws DataValidationException, SQLException {
        String longName = "NameIsTooLongForAGoal";
        try {UserInterface.editGoalName(testGoal, longName);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally{
            reloadTestGoal();
            Assert.assertFalse((testGoal.getGoalName().equals(longName)),
                    "Edit Goal Name error: can edit with name more than 20 symbols");
        }

    }

    @Test
    public void editGoalDescValid() throws DataValidationException, SQLException {
        //More than 10 characters, less than 50
        String oldGoalDesc = testGoal.getGoalDescription();
        String validDescription = "ValidDescription";
        if(!Objects.equals(oldGoalDesc, validDescription))
            UserInterface.editGoalDescription(testGoal, validDescription);
        else {
            validDescription = validDescription.replace(validDescription.charAt(1), validDescription.charAt(validDescription.length()-1));
            UserInterface.editGoalDescription(testGoal, validDescription);
        }

        reloadTestGoal();
        Assert.assertEquals(testGoal.getGoalDescription(), validDescription,
                "Edit Goal Description error: can not edit with valid data");

    }

    @Test
    public void editGoalDescNull() throws DataValidationException, SQLException {
        String nullDesc = null;
        try {UserInterface.editGoalDescription(testGoal, nullDesc);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Exeption was thrown and it's OK!\n");
        } finally {
            reloadTestGoal();
            Assert.assertNotNull(testGoal.getGoalDescription(), "Edit Goal Description error: can edit to null");
        }

    }

    @Test
    public void editGoalDescInvalidShort() throws DataValidationException, SQLException {

        String shortDesc = "123456789";
        try {UserInterface.editGoalDescription(testGoal, shortDesc);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Exeption was thrown and it's OK!\n");
        } finally{
            reloadTestGoal();
            Assert.assertFalse((testGoal.getGoalDescription().equals(shortDesc)),
                    "Edit Goal Description error: can edit with description less than 10 symbols");
        }

    }

    @Test
    public void editGoalDescInvalidLong() throws DataValidationException, SQLException {
        String longDesc = "1234567890 1234567890 1234567890 1234567890 1234567890";
        try {UserInterface.editGoalDescription(testGoal, longDesc);}
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Exeption was thrown and it's OK!\n");
        } finally {
            reloadTestGoal();
            Assert.assertFalse((testGoal.getGoalDescription().equals(longDesc)),
                    "Edit Goal Description error: can edit with description more than 50 symbols");
        }


    }

    @Test
    public void editReachedFlag() throws SQLException, DataValidationException {
        boolean oldFlag = testGoal.getIsReachedFlag();
        boolean newFlag = !oldFlag;
        UserInterface.editGoalReachedFlag(testGoal, newFlag);
        reloadTestGoal();
        Assert.assertEquals(newFlag, testGoal.getIsReachedFlag(),
                "Edit IsReached parameter error: can not change flag");

    }

    @Test
    public void addGoalValid() throws DataValidationException, SQLException {
        String name = "ValidGoalName";
        String desc = "This is a Goal Description";
        int oldSize = testGoals.size();

        UserInterface.addGoal(name, desc);

        reloadTestGoal();
        int newSize = testGoals.size();

        Assert.assertEquals(oldSize + 1, newSize, "Error: goal list size did not change");
        Assert.assertEquals(testGoals.get(newSize - 1).getGoalName(), name, "Name of the added goal does not match input name");
        Assert.assertEquals(testGoals.get(newSize - 1).getGoalDescription(), desc,
                "Description of the added goal does not match input name");

    }

    @Test
    public void addGoalInvalid() throws DataValidationException, SQLException {
        String invName = "Name1";
        String invDesc = "InvDesc";
        String validName = "ValidName";
        String validDesc = "ValidDescription";

        int oldSize = testGoals.size();

        try {
            UserInterface.addGoal(invName, validDesc);
        }
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally {
            reloadTestGoal();
            int newSize = testGoals.size();
            Assert.assertTrue(newSize == oldSize, "Goal with invalid name was added!");

        }

        try {
            UserInterface.addGoal(validName, invDesc);
        }
        catch (Exception e) {
            System.out.println( e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Exeption was thrown and it's OK!\n");
        }
        finally {
            reloadTestGoal();
            int newSize = testGoals.size();
            Assert.assertTrue(newSize == oldSize, "Goal with invalid description was added!");

        }

    }

    @AfterMethod
    public void tearDown() throws Exception {
        //Not sure if we should use methods from class under test to setup and teardown test
        UserInterface.close();
    }

    //TODO: restore database to initial state

    //Some helper methods
    public void reloadTestGoal() throws DataValidationException, SQLException {
        testGoals = UserInterface.getGoals();
        testGoal = testGoals.get(randomGoalID);
    }
}
