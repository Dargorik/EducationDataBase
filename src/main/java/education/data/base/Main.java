package education.data.base;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        DBWorker worker=new DBWorker();
        String query=("select * from users");
        try {
            Statement statement=worker.getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            while(resultSet.next()){
                User user=new User();
                user.setID(resultSet.getInt(1));
                user.setUserName(resultSet.getString(4));
                user.setUserPassword(resultSet.getString(5));
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
