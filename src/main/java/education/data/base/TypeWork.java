package education.data.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeWork implements Table{

    private static final String URL = "jdbc:mysql://localhost:3306/supervisorsystem?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public  List<TypeWork> getListTable() {
        List<TypeWork> listTypeWork = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement("select * FROM types_of_work;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TypeWork tw = new TypeWork();
                tw.setId(rs.getInt(1));
                tw.setName(rs.getString(2));
                listTypeWork.add(tw);
            }
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return listTypeWork;
    }
}

