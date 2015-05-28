package javasrc;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private long userID;
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;

    public User(long id, String fN, String lN) {
        userID = id;
        firstName = fN;
        lastName = lN;
    }

    public long getUserID() {
        return this.userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    ArrayList<Goal> getGoals() throws SQLException, DataValidationException {
        return Database.getUserGoals(this);
    }

    public String toString() {
        String nickname = this.getNickName();
        if(nickname == null) nickname = "";
        String phonenumber = this.getPhoneNumber();
        if(phonenumber == null) phonenumber = "";

        return("User ID: " + this.getUserID() + "\n"
                + "First name: " + this.getFirstName() + "\n"
                + "Last name: " + this.getLastName() + "\n"
                + "Nickname: " + nickname + "\n"
                + "Phone number: " + phonenumber + "\n\n");
    }



}
