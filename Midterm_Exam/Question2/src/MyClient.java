import java.io.*;
import java.net.*;

public class MyClient {

    public static void main(String[] args) {

        //Only one connection and one port # is needed since the chat is bi-directional
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 16790);

            System.out.println("Client Side Dialogue: \n");

            //On each side we need 2 different thread classes
            //one for the sender and one for the receiver
            //Both threads will run simultaneously

            //Thread to send messages to Client
            ClientSender send = new ClientSender(clientSocket);
            Thread sThread = new Thread(send);
            sThread.start();

            //Thread to receive messages from Client
            ClientReceiver receive = new ClientReceiver(clientSocket);
            Thread rThread = new Thread(receive);
            rThread.start();

        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

    }

}
