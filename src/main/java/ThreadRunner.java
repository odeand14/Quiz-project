import java.io.*;
import java.net.Socket;
import java.util.List;


// Created by Andreas Odegaard on 31.10.2016.

/**
 * Class for determining thread behavior and communication with server.
 */
public class ThreadRunner implements Runnable{

    private Socket serverSocket;
    private int scoreCounter = 0, rand = 0;
    private String question;
    private QuestionGenerator q = new QuestionGenerator();
    private List<Artist> artists;
    private Artist artist;
    private DataInputStream in;
    private PrintWriter messageOut;

    public ThreadRunner(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }


    /**
     * Checks user input vs answer and removes casing and spaces first.
     * @param answer answer given by client
     * @param correctAnswer correct answer from object
     * @return String with correct/wrong prompt
     */
    public String checkAnswer(String answer, String correctAnswer) {

        if (answer.toLowerCase().replaceAll("\\s+","").equals(correctAnswer.toLowerCase().replaceAll("\\s+",""))) {
            scoreCounter++;
            return  "Correct answer!";
        } else return "Wrong answer :(";
    }

    /**
     * assigns random Artist object and question to field.
     */
    public void setupRandomQuestion() {
        artist = q.getRandomArtistRow(artists);
        rand = q.getQuestionNumber();
        question = q.getQuestionFromRow(artist, rand);
    }

    /**
     * Attempts to set input/output streams.
     */
    public void setupInputOutput() {
        try {
            in = new DataInputStream(this.serverSocket.getInputStream());
            messageOut = new PrintWriter(this.serverSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not connect to serversocket, check your server settings.");
        }
    }

    /**
     * Checks for available input
     * @return boolean
     */
    public boolean inputStreamAvailable() {
        try {
            if (in.available() > 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No input stream available.");
        }
        return false;
    }

    /**
     * Attempts to read message from client.
     * @return message as String
     */
    public String readMessage() {
        String message = "";
        try {
            message = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No incoming message.");
        }
        return message;
    }

    /**
     * the run method for each thread, it runs until shut down and starts by sending and initial question to the Client.
     * Then it sends and receives strings to and from the Client and checks the aswer given with the correct answer.
     */
    public void run() {
        boolean doOnce = false;
        String correctAnswer;
        artists = q.getAllArtists();
        setupInputOutput();

        while (true) {

            if (!doOnce) {
                setupRandomQuestion();
                messageOut.println(question);
                doOnce = true;
            }

            if (inputStreamAvailable()) {
                correctAnswer = q.getAnswerToQuestion(artist, rand);
                String userInput = readMessage();

                messageOut.println(correctAnswer);
                messageOut.println(checkAnswer(userInput, correctAnswer));
                messageOut.println(scoreCounter);
                setupRandomQuestion();
                messageOut.println(question);
            }
        }
    }

}
