import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args){
        ServerSocket serverSocket1 = null;
        ServerSocket serverSocket2 = null;
        try {
            serverSocket1 = new ServerSocket(16790);
            serverSocket2 = new ServerSocket(16791);
            Socket clientSocket1 = null;
            Socket clientSocket2 = null;
            clientSocket1 = serverSocket1.accept();
            clientSocket2 = serverSocket2.accept();
            PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
            PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
            out1.println("I am the server - Port 16790");
            out2.println("I am the server - Port 16791");
            out1.close();
            out2.close();
            clientSocket1.close();
            clientSocket2.close();
            serverSocket1.close();
            serverSocket2.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }
}
