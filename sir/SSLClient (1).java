package module1;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;

public class SSLClient {

    public static void main(String[] args) throws Exception {
        String serverHost = "localhost";
        int serverPort = 12345;

        // Set up trust manager factory to use the server's certificate
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore trustStore = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\NPLAB\\src\\module1\\keystore.jks");
        trustStore.load(fis, "password".toCharArray());
        tmf.init(trustStore);

        // Set up SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        // Create socket factory
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        // Create socket and connect to server
        SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(serverHost, serverPort);

        // Write data to server
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.println("Hello, server!");
        out.flush();
        System.out.println("Sent to server: Hello, server!");

        // Close streams and socket
        out.close();
        socket.close();
    }
}

