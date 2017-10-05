
import com.j256.ormlite.logger.LocalLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

// Created by Andreas on 23.11.2016.


/**
 * Creates the client GUI and connects it to the server.
 */
public class Client extends JFrame implements ActionListener
{
    private DataOutputStream dos;
    private BufferedReader input;
    private String question;
    private String textString;
    private final JTextField txtQuestionField, txtAnswerField, txtWinLoose, txtCorrectCounter, txtCorrectAnswer;
    private JButton btnAnswer, btnExit, btnShowHighScore;
    private Highscore userStats = new Highscore("Unknown", 0);
    private Socket socket;


    /**
     * Constructor for JFrame visualization of the client communication to the server.
     */
    public Client() {
        setTitle("Music Quiz");
        setSize(600,250);

        JPanel pnlInnerTop = new JPanel(new GridLayout(2, 2));
        JPanel pnlInnerBottom = new JPanel(new GridLayout(1, 2));
        JPanel pnlInnerBottomLeft = new JPanel(new GridLayout(1, 2));
        JPanel pnlOuter = new JPanel(new GridLayout(3, 1));

        txtQuestionField = new JTextField();
        btnAnswer = new JButton("Answer!");
        btnExit = new JButton("Exit and save");
        btnShowHighScore = new JButton("Show Highscore");
        txtAnswerField = new JTextField();
        txtWinLoose = new JTextField();
        txtCorrectCounter = new JTextField();
        txtCorrectAnswer = new JTextField();

        txtQuestionField.setEditable(false);
        txtCorrectCounter.setEditable(false);
        txtWinLoose.setEditable(false);
        txtCorrectAnswer.setEditable(false);
        txtQuestionField.setSize(600, 50);

        pnlInnerTop.add(txtAnswerField);
        pnlInnerTop.add(txtWinLoose);
        pnlInnerTop.add(btnAnswer);
        pnlInnerTop.add(txtCorrectCounter);

        btnAnswer.addActionListener(this);
        btnExit.addActionListener(this);
        btnShowHighScore.addActionListener(this);

        pnlInnerBottomLeft.add(btnShowHighScore);
        pnlInnerBottomLeft.add(btnExit);

        pnlInnerBottom.add(txtCorrectAnswer);
        pnlInnerBottom.add(pnlInnerBottomLeft);

        pnlOuter.add(txtQuestionField);
        pnlOuter.add(pnlInnerTop);
        pnlOuter.add(pnlInnerBottom);

        JDialog initialPrompt = new JDialog();
        initialPrompt.setAlwaysOnTop(true);
        userStats.setUsername(JOptionPane.showInputDialog(
                initialPrompt, "Enter username", "Music Quiz", JOptionPane.PLAIN_MESSAGE));

        add(pnlOuter);

        pnlInnerTop.getRootPane().setDefaultButton(btnAnswer);

        setInputOutput();

        txtQuestionField.setText(question);
        txtCorrectCounter.setText("Your score is: 0");

        setVisible(true);
      // pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
    }

    /**
     * Sets input/output variables using properties file.
     */
    public void setInputOutput() {
        Properties properties = DBloader.loadPropertiesFile();
        int port = Integer.parseInt(properties.getProperty("SERVER.port"));
        String address = properties.getProperty("SERVER.address");

        try {
            socket = new Socket(address, port);
            dos = new DataOutputStream(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            question = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is a problem with your server address " + address + " or port " + port);
        }
    }

    /**
     * Launches pop-up window with Highscorelist
     */
    public void showHighscoreList() {
        JTextArea textArea = new JTextArea(
                HighscoreHandler.printHighscores(
                        HighscoreHandler.getHighscoreList()));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 300, 300 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Highscore list",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * actionPerformed for button, fetches and sends data through the thread, and sets the values in the GUI.
     * @param args
     */
    public void actionPerformed(ActionEvent args) {
        if (args.getActionCommand().equals("Exit and save")) {
            HighscoreHandler.updateHighscoreTable(userStats);
            System.exit(0);
        }
        if (args.getActionCommand().equals("Show Highscore")) {
            showHighscoreList();
        }

        if (args.getActionCommand().equals("Answer!"))
        try
        {
            textString = txtAnswerField.getText();
            dos.writeUTF(textString);
            dos.flush();
            txtCorrectAnswer.setText("The Answer was: " + input.readLine());
            txtWinLoose.setText(input.readLine());
            userStats.setScore(Integer.parseInt(input.readLine()));
            txtCorrectCounter.setText("Your score is: " + userStats.getScore());
            txtQuestionField.setText(input.readLine());
            txtAnswerField.setText("");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is an error in receiving/sending text to the server. make sure it's running");
        }
    }

    /**
     * Starts Client application.
     */
    public static void main(String[] args)
    {
        new Client();
    }

}