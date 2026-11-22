
package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    private static final String USERNAME = "ahss";

    private static final String PASSWORD = System.getenv("AHSS_DB_PASSWORD");

    public static Connection getConnection() {

        if (PASSWORD == null || PASSWORD.isBlank()) {
            System.out.println("Database Password Environment Variable Not Found!");
            return null;
        }

        try {

            Connection con = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            System.out.println(e.getMessage());

            return null;
        }
    }
}
