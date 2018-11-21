package education.data.base;

public class User {
    private int ID;
    private String userName;
    private String userPassword;

    User(){}

    User(String userName,String uuerPassword){
        this.userName=userName;
        this.userPassword=uuerPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "id="+ID+"\nUser name- "+userName+"\nUser Password- "+userPassword;
    }
}
