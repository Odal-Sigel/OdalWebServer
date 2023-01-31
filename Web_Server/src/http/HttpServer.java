package http;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        int port = 9000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Odal Web Server is running on port " + port + "...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");
            Scanner sc = new Scanner(clientSocket.getInputStream());
            String line;
            while ((line = sc.nextLine())!=null) {
                System.out.println(line);
                if (line.isEmpty()) {
                    break;
                }
            }

            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<b>Hello World!</b>".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();

            System.out.println("Client connection closed");
            sc.close();
            clientOutput.close();
        }
    }
}
