package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lmstask2","postgres","nurlan21");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
