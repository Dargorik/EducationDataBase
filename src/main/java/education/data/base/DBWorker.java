package education.data.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBWorker {
    PreparedStatement preparedStatement = null;

    private static final String URL = "jdbc:mysql://localhost:3306/supervisorsystem?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_NEW_USER = "INSERT INTO users(first_name, last_name, login, password, activ, position) VALUES(?,?,?,?,?,(select positions.idpositions from positions where positions.name=?))";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS = "DELETE FROM users WHERE ";
    private static final String DELETE_ALL_USERS = "DELETE FROM users";

    Connection connection;

    public Connection getConnection() {
        return connection;
    }


    public DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            /*Statement statment = connection.createStatement();
            //statment.executeQuery("select * from streets");
            statment.addBatch("select * from streets");
            statment.addBatch("select * from streets");
            statment.executeBatch();
            statment.clearBatch();

            statment.getConnection();

            statment.close();

            if (!connection.isClosed())
                System.out.println("Подключение установленно");
            connection.close();*/
        } catch (
                SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера!");
        }
    }


    public void inserеNewUser(String firstNamne, String lastName, String login, String password, Boolean active, String position) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, firstNamne);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setBoolean(5, active);
            preparedStatement.setString(6, position);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = rs.next() ? rs.getInt(1) : 0;

            if (key != 0) {
                System.out.println("Generated key=" + key);
                }
            } catch(SQLException e){
                e.printStackTrace();
        }

    }

    public List<User> selectAllwUser() throws SQLException {
        List<User> users=new ArrayList<User>();
        try {
            preparedStatement=connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setID(rs.getInt(1));
                user.setFirst_name(rs.getString(2));
                user.setLast_name(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setPassord(rs.getString(5));
                user.setActiv(rs.getBoolean(6));
                PreparedStatement preparedStatement2=connection.prepareStatement("SELECT name FROM positions WHERE idpositions="+rs.getString(7));
                ResultSet rs2=preparedStatement2.executeQuery();
                while (rs2.next()){
                    user.setPosition(rs2.getString(1));
                }


                users.add(user);
                //System.out.println(user.toString());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(String s) throws SQLException {
        try {
            preparedStatement=connection.prepareStatement(DELETE_USERS+s);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteAllUser() throws SQLException {
        try {
            preparedStatement=connection.prepareStatement(DELETE_ALL_USERS);
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addPositionsDuties(String position, String type) throws SQLException {
        try {
            preparedStatement=connection.prepareStatement(
                    "insert into positions_duties (position, type_of_work) values((select idpositions from positions where positions.name='"+position+
                            "'),(select idtypes_of_work from types_of_work where types_of_work.name='"+type+"'));"
            );
            preparedStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public <T> List<T> getListTable(T t){
        //System.out.println(t.getClass());
        try {
            Method met=t.getClass().getDeclaredMethod("getListTable");
            return (List<T>) met.invoke(t);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
