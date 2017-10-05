
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// Created by Andreas on 23.11.2016.
public class QuestionGeneratorTest {

    @org.junit.Before
    public void setUp() throws Exception {
        assertNotNull(DBloader.getConnectionSource());
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void getAllArtists() throws Exception {
        QuestionGenerator qestionGen = new QuestionGenerator();
        Artist tmpArtist = new Artist("LFO", "Summer Girls", "LFO", "America", 1999);
        List<Artist> list = qestionGen.getAllArtists();

        assertEquals(list.size(), 30);
        assertTrue(list.contains(tmpArtist));
    }

    @org.junit.Test
    public void getRandomArtistRow() throws Exception {
        QuestionGenerator questionGen = new QuestionGenerator();
        List<Artist> list = questionGen.getAllArtists();
        Artist artist = questionGen.getRandomArtistRow(list);

        assertNotNull(artist);
        assertNotEquals(artist, new Artist());
        assertTrue(list.contains(artist));

    }

    @org.junit.Test
    public void getQuestionNumber() throws Exception {
        QuestionGenerator questionGen = new QuestionGenerator();
        int i = questionGen.getQuestionNumber();
        ArrayList<Integer> allNumbers = new ArrayList<Integer>();
        for (int j = 0; j <= 4; j++) {
            allNumbers.add(j);
        }
        assertTrue(allNumbers.contains(i));
        assertTrue(i <= 4 && i >= 0);
        assertNotNull(i);
    }

    @org.junit.Test
    public void getQuestionFromRow() throws Exception {
        QuestionGenerator questionGen = new QuestionGenerator();
        List<Artist> list = questionGen.getAllArtists();
        Artist artist = questionGen.getRandomArtistRow(list);
        int i = questionGen.getQuestionNumber();
        String s = questionGen.getQuestionFromRow(artist, i);

        assertTrue(s.getClass().equals(String.class));
        assertNotNull(s);
        assertNotEquals(s, "");
    }

    @org.junit.Test
    public void getAnswerToQuestion() throws Exception {
        QuestionGenerator questionGen = new QuestionGenerator();
        List<Artist> list = questionGen.getAllArtists();
        Artist artist = questionGen.getRandomArtistRow(list);
        int i = questionGen.getQuestionNumber();
        String s = questionGen.getAnswerToQuestion(artist, i);

        assertTrue(s.getClass().equals(String.class));
        assertNotNull(s);
        assertNotEquals(s, "");
    }

}