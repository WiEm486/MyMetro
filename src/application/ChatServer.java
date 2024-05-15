package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private ServerSocket serverSocket;
   
    private List<ClientHandler> clients = new ArrayList<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (true) {
            // Wait for a new client connection
            Socket clientSocket = serverSocket.accept();

            // Create a new client handler for the client
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            clients.add(clientHandler);

            // Start the client handler thread
            new Thread(clientHandler).start();
        }
    }

    public void broadcast(String message, ClientHandler sender) {
        // Send the message to all clients except the sender
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(sender.getUsername() + ": " + message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.start(5000);
    }
}
