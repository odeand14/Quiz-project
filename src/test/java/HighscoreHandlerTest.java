import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// Created by Andreas Odegaard on 07.12.2016.

/**
 * demands having run the DB script.
 */
public class HighscoreHandlerTest {

    @Test
    public void getHighscoreList() throws Exception {
        List<Highscore> highscores = HighscoreHandler.getHighscoreList();

        assertNotNull(highscores);
        assertTrue(highscores.contains(new Highscore("Jon", 10)));
    }

    @Test
    public void updateHighscoreTable() throws Exception {
        Highscore h = new Highscore("Lollo", 9);
        HighscoreHandler.updateHighscoreTable(h);
        List allScores = HighscoreHandler.getHighscoreList();

        assertNotNull(allScores);
        assertTrue(allScores.contains(h));
    }

    @Test
    public void printHighscores() throws Exception {
        HighscoreHandler.updateHighscoreTable(new Highscore("God", 999));
        List<Highscore> highscores = HighscoreHandler.getHighscoreList();
        String highscoreOutput = HighscoreHandler.printHighscores(highscores);

        assertTrue(highscoreOutput.startsWith("1: username: God\tscore: 999"));
    }

}