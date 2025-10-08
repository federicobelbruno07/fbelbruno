package network;
import java.io.*;
import java.net.*;

public class TcpServer {
    public static void main(String[] args) {
        int port = 6789; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server avviato. In attesa di connessioni...");

            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connessione accettata da: " + connectionSocket.getInetAddress());

            BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(
                connectionSocket.getOutputStream());

            String clientSentence;
            while ((clientSentence = inFromClient.readLine()) != null) {
                System.out.println("Ricevuto dal client: " + clientSentence);
                outToClient.writeBytes(clientSentence.toUpperCase() + '\n'); 
            }

            connectionSocket.close();
            System.out.println("Connessione terminata.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
