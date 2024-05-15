package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket socket;
    private ChatServer chatServer;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private static int count = 0;
    private int id;
    public ClientHandler(Socket socket, ChatServer chatServer) throws IOException {
    	
        this.socket = socket;
        this.chatServer = chatServer;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        count++;
        id = count;
        username = "user"+id;
    }


	public String getUsername() {
        return username;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
    
            while (true) {
            
           
                String message = in.readLine();
                if (message == null) {
                    chatServer.removeClient(this);
                    break;
                }
                chatServer.broadcast(message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}