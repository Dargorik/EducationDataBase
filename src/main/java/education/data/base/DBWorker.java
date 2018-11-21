package education.data.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBWorker {

    private static final String URL = "jdbc:mysql://localhost:3306/supervisorsystem?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker()
    {
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
}
