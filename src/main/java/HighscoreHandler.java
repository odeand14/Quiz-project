import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

// Created by Andreas Odegaard on 07.12.2016.

/**
 * Class for Highscore object manipulation, updating and downloading from DB and formating.
 */
public class HighscoreHandler {

    public HighscoreHandler() {}

    /**
     * Tries to connect to the database and fetch all the data from it using ormLite and own Highscore class.
     * @return List of Highscores
     */
    public static List<Highscore> getHighscoreList() {
        JdbcConnectionSource con = DBloader.getConnectionSource();
        try {
            assert con != null;
            Dao<Highscore, String> highscoreDao = DaoManager.createDao(con, Highscore.class);
            return highscoreDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is probably something wrong with your Highscore class, please make sure it fits the database table");
        }
        try {
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the database table with a new Highscore
     * @param highscore Highscore for table insertion
     */
    public static void updateHighscoreTable(Highscore highscore) {
        JdbcConnectionSource con = DBloader.getConnectionSource();
        assert con != null;
        try {
            Dao<Highscore, String> highscoreDao = DaoManager.createDao(con, Highscore.class);
            highscoreDao.create(highscore);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Formats, sorts and prints a list of Highscores
     * @param highscores List of Highscore objects
     * @return Formated String
     */
    public static String printHighscores(List<Highscore> highscores) {
        String list = "";
        int counter = 1;
        Collections.sort(highscores);
        for (Highscore h: highscores) {
            list += counter + ": " + h + "\n";
            counter++;
        }
        return list;
    }


}
