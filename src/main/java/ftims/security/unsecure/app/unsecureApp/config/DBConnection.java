package ftims.security.unsecure.app.unsecureApp.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bryka on 24.05.18.
 */
@Component
public class DBConnection {
    private static final String HOST = "jdbc:mysql://localhost:3306/UnsecureAppDb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "bryka123";

    private Connection connection = null;

    public Connection getConnection() throws SQLException {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            } catch (SQLException er) {
                System.out.println(er.getErrorCode());
                System.out.println(er.getMessage());
            }
        }
        return connection;
    }
}
