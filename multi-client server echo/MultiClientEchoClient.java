import java.io.*;
import java.net.*;

public class MultiClientEchoClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server.");
            System.out.println("Type a message and press Enter to send. Type 'exit' to quit.");

            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                if (userMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(userMessage);
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
