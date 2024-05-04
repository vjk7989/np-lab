package module1;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;

public class SSLServer {

    public static void main(String[] args) throws Exception {
        int port = 12345;

        // Load keystore
        char[] password = "password".toCharArray();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\NPLAB\\src\\module1\\keystore.jks");
        keyStore.load(fis, password);

        // Set up key manager factory to use the keystore
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, password);

        // Set up SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        // Create server socket factory
        SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

        // Create server socket
        SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);

        // Accept client connections
        System.out.println("Server started. Listening on port " + port + "...");
        SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
        System.out.println("Client connected.");

        // Read data from client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = in.readLine();
        System.out.println("Received from client: " + message);

        // Close streams and sockets
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
