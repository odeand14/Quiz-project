
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

// Created by Andreas on 14.11.2016.

/**
 * Class establishing a running server, ready to receive connections.
 */
public class Server {



    /**
     * Starts a server which runs until stopped,
     * it listens on given port for requests and creates a new ThreadRunner with each connection.
     * Then starts it.
     */
    public Server() {
        Properties properties = DBloader.loadPropertiesFile();
        int port = Integer.parseInt(properties.getProperty("SERVER.port"));
        ServerSocket serverSocket = null;
        Thread thread = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server running, awaiting connections.");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecting: " + socket.getInetAddress());
                ThreadRunner threadRunner = new ThreadRunner(socket);
                thread = new Thread(threadRunner);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is possibly a problem with your serversocket: " + serverSocket);
        } finally {
            assert serverSocket != null;
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (thread != null) {
                thread.interrupt();
            }
        }
    }

    /**
     * Starts Server application.
     */
    public static void main(String[] args) {

        new Server();
    }


}
