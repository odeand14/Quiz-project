import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Created by Andreas Odegaard on 07.12.2016.
public class ThreadRunnerTest {
    private ThreadRunner threadRunner = new ThreadRunner(new Socket());

    @Test
    public void checkAnswer() throws Exception {
        String correctAnswer = "ABBA", userAnswer = " abba ";

        assertTrue(threadRunner.checkAnswer(userAnswer, correctAnswer).equals("Correct answer!"));
        assertTrue(threadRunner.checkAnswer("Prince", correctAnswer).equals("Wrong answer :("));

    }

    @Test(expected = NullPointerException.class)
    public void inputStreamAvailable() {
        threadRunner.inputStreamAvailable();
        assertFalse(threadRunner.inputStreamAvailable());
    }

    @Test(expected = NullPointerException.class)
    public void readMessage() {
        threadRunner.readMessage();
    }

}