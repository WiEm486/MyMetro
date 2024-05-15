package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatRoomController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    private ListView<String> userList;

    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    public String nameUSER;
    public void initialize() {
        // Connect to the server
        try {
            socket = new Socket("localhost", 5000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start a new thread to listen for incoming messages
        new Thread(() -> {
            while (in.hasNextLine()) {
                String message = in.nextLine();
                chatArea.appendText(message + "\n");
            }
        }).start();
    }

    @FXML
    public void sendMessage() {
        // Send the message to the server
        String message = messageField.getText();
        System.out.println(message);
        out.println(message);
        out.flush();
        chatArea.appendText(message+"\n");
        messageField.setText("");
    }

	public void init(String nameUser) {
		nameUser=nameUser;
		
	}
	public String getUserName()
	{
		return nameUSER;
	}
}
