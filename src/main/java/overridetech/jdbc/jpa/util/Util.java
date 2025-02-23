package overridetech.jdbc.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {

        final String url = "jdbc:postgresql://localhost:5432/Auth";
        final String name = "postgres";
        final String password = "qwerty12356";
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

}
