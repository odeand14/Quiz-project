import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

// Created by Andreas Ødegaard on 31.10.2016.

/**
 * Class for handling DB connection and property file reading.
 */
public class  DBloader {

    public DBloader() {
    }

    /**
     * Loads the contents of db.properties using loadPropertiesFile() method and tries to connect to database
     * @return JdbcConnectionSource
     */
    public static JdbcConnectionSource getConnectionSource() {
        Properties properties = loadPropertiesFile();
        String DB_url = properties.getProperty("JDBC.url");
        String user = properties.getProperty("JDBC.user");
        String password = properties.getProperty("JDBC.password");
        try {
            return new JdbcConnectionSource(DB_url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is an error in your db.properties file. Please revisit the following strings:\nUrl: " + DB_url + "\nUser: " + user + "\nPassword: " + password);
        }
        return null;
    }

    /**
     * Reads db.properties file and returns suitable object with information
     * @return Properties
     */
    public static Properties loadPropertiesFile() {
        Properties prop = new Properties();
        try {
            InputStream in = new FileInputStream("db.properties");
            prop.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file you are trying to access: \"db.properties\" please make sure the name/filepath is correct.");
        } catch (IOException e) {
            System.out.println("Can't load properties from file.");
        }
        return prop;
    }
}
