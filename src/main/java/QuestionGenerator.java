
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

// Created by Andreas on 21.11.2016.

/**
 * Class for question/answer generation
 */
public class QuestionGenerator {

    private Random rand = new Random();

    public QuestionGenerator() {
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
    }

    /**
     * Tries to connect to the database and fetch all the data from it using ormLite and own Artist class.
     * @return List of Artist objects
     */
    public List<Artist> getAllArtists() {
        JdbcConnectionSource con = DBloader.getConnectionSource();
        try {
            assert con != null;
            Dao<Artist, String> artistDao = DaoManager.createDao(con, Artist.class);
            return artistDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is probably something wrong with your Artist class, please make sure it fits the database table");
        }
        try {
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes a list of Artist objects and returns a random object from the list.
     * @param artistList List of Artist objects
     * @return random Artist
     */
    public Artist getRandomArtistRow(List<Artist> artistList) {
        int size = artistList.size();
        return artistList.get(rand.nextInt(size));
    }

    /**
     * Generates a random number between 0 and 4
     * @return int
     */
    public int getQuestionNumber() {
        return rand.nextInt(5);
    }

    /**
     * Creates a question using an Artist object and predefined outputs
     * @param artist Artist object
     * @param number number between 0 and 4
     * @return String with generated question
     */
    public String getQuestionFromRow(Artist artist, int number) {
        switch (number) {
            case 0:
                return "Which artist wrote " + artist.getSong() + " from the album " + artist.getAlbum() + "?";
            case 1:
                return "What year did " + artist.getName() + " release " + artist.getAlbum() + "?";
            case 2:
                return "Which album contains " + artist.getSong() + " written by " + artist.getName() + "?";
            case 3:
                return "What country does " + artist.getName() + " come from?";
            case 4:
                return "Which artist released the album " + artist.getAlbum() + " in " + artist.getYear()+ "?";
        }
        return "You need to insert a number between 0 and 4";
    }

    /**
     * Creates an answer to a question using an Artist object and its getMethods
     * @param artist Artist object
     * @param number number between 0 and 4
     * @return String with generated answer.
     */
    public String getAnswerToQuestion(Artist artist, int number) {
        switch (number) {
            case 0:
                return  artist.getName();
            case 1:
                return  "" + artist.getYear();
            case 2:
                return  artist.getAlbum();
            case 3:
                return  artist.getNationality();
            case 4:
                return artist.getName();
        }
        return "You need to insert a number between 0 and 4";
    }


}
