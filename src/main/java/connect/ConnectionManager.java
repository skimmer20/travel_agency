package connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
public class ConnectionManager {

    private static Connection connection;

    private ConnectionManager() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = openConnection();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static Connection openConnection() throws
            ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Properties properties = new Properties();
        String driver = null;
        String url = null;
        String userName = null;
        String password = null;
        String file = Objects.requireNonNull(
                ConnectionManager.class.getClassLoader().getResource("db.properties")).getFile();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            driver = properties.getProperty("db.driver"); // or com.mysql.cj.jdbc.Driver
            url = properties.getProperty("db.url");
            userName = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class.forName(driver).newInstance();
        assert url != null;
        return DriverManager.getConnection(url, userName, password);
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
