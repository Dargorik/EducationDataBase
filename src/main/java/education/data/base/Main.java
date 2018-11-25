package education.data.base;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBWorker worker = new DBWorker();
        List<User> users = new ArrayList<User>();
        //String query=("select * from users");
        try {
           /* Statement statement=worker.getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            while(resultSet.next()){
                User user=new User();
                user.setID(resultSet.getInt(1));
                user.setUserName(resultSet.getString(4));
                user.setUserPassword(resultSet.getString(5));
                System.out.println(user.toString());
            }*/
           // worker.deleteUser("first_name = 'Oleg'");
            //worker.deleteAllUser();
            //worker.inserеNewUser("Андрей", "Доронин", "noga", "noga", true, "менеджер");
            //worker.addPositionsDuties("менеджер","обход");
            users.clear();
            users.addAll(worker.selectAllwUser());
            users.stream().filter(x->x.getFirst_name().equals("Андрей")).forEach(System.out::println);
            worker.getListTable(new TypeWork()).stream().forEach(System.out::println);
            worker.getListTable(new User()).stream().forEach(System.out::println);
            // worker.inserter();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
