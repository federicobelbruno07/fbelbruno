package network;
import java.io.*;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 6789;

        try (Socket clientSocket = new Socket(serverAddress, port);
             BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Connesso al server. Scrivi un messaggio (CTRL+C per uscire):");

            String sentence;
            while ((sentence = inFromUser.readLine()) != null) {
                outToServer.writeBytes(sentence + '\n');
                String modifiedSentence = inFromServer.readLine();
                System.out.println("Dal server: " + modifiedSentence);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
