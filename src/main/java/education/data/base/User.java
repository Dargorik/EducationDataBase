package education.data.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User implements  Table{

    private static final String URL = "jdbc:mysql://localhost:3306/supervisorsystem?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private int ID;
    private String first_name;
    private String last_name;
    private String login;
    private String passord;
    private Boolean activ;
    private  String position;

    User(){}

    User(String first_name,String last_name){
        this.first_name=first_name;
        this.last_name=last_name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public Boolean getActiv() {
        return activ;
    }

    public void setActiv(Boolean activ) {
        this.activ = activ;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String post) {
        this.position = post;
    }

    @Override
    public String toString() {
        return "id-"+ID+" [Имя-"+first_name+" фамилия-"+last_name+" логин-"+login+" пароль-"+passord+" активность-"+activ+" должность-"+position+"]";
    }

    public  List<User> getListTable() {
        List<User> listUser = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setID(rs.getInt(1));
                user.setFirst_name(rs.getString(2));
                user.setLast_name(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setPassord(rs.getString(5));
                user.setActiv(rs.getBoolean(6));
                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT name FROM positions WHERE idpositions=" + rs.getString(7));
                ResultSet rs2 = preparedStatement2.executeQuery();
                while (rs2.next()) {
                    user.setPosition(rs2.getString(1));
                }
                listUser.add(user);
            }

                connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
}
