package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){

        try {
            Properties props = loadProps();
            String user = props.getProperty("user");
            String url = props.getProperty("dburl");
            String password = props.getProperty("password");

            conn = DriverManager.getConnection(url,user,password);

            return conn;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Properties loadProps(){
        Properties props = new Properties();

        try(FileInputStream fileInputStream = new FileInputStream("db.properties")){

            props.load(fileInputStream);
            return props;

        }catch (IOException e){
            throw new ExceptionDB(e.getMessage());
        }

    }

    public static void closeConnection(){

        if (conn != null){
            try {
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
