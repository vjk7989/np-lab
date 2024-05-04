import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiClientEchoServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new EchoClientHandler(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}

class EchoClientHandler implements Runnable {
    private final Socket clientSocket;

    EchoClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Client connected: " + clientAddress);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from " + clientAddress + ": " + inputLine);
                out.println("Echo: " + inputLine);
            }

            System.out.println("Client disconnected: " + clientAddress);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
