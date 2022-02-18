package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String DBUrl = null;
    private static String DBUser = null;
    private static String DBPassword = null;

    public static Connection con = null;

    public static Connection getConnection() {
        Properties proper = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            proper.load(fis);
            DBUrl = proper.getProperty("db.host");
            DBUser = proper.getProperty("db.user");
            DBPassword = proper.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
