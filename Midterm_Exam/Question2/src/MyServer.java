import java.io.*;
import java.net.*;

public class MyServer {

    public static void main(String[] args) {

        //Only one connection and one port # is needed since the chat is bi-directional
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(16790);
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();

            System.out.println("Server Side Dialogue: \n");

            //On each side we need 2 different thread classes
            //one for the sender and one for the receiver
            //Both threads will run simultaneously

            //Thread to send messages to Client
            ServerSender send = new ServerSender(clientSocket);
            Thread sThread = new Thread(send); //need to cast runnable as thread in order to use .start()
            sThread.start();

            //Thread to receive messages from Client
            ServerReceiver receive = new ServerReceiver(clientSocket);
            Thread rThread = new Thread(receive);
            rThread.start();

        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

    }

}
