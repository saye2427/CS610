import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = null;
        BufferedReader in = null;
        System.out.println("Welcome to the Daytime client.");

        boolean control = true;
        boolean isPrinted = false;
        while(control) {

            try {
                clientSocket = new Socket("localhost", 4321);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String s = in.readLine();
                System.out.println("\nConnected!");
                System.out.println("Here is the timestamp received from the server: "+s);
                in.close();
                clientSocket.close();
                clientSocket.close();
                control = false;
            } catch (ConnectException e) { //changed to a ConnectException which checks to see if a connection was made
                while (!isPrinted) { //place in a loop with boolean to control so it is only printed once
                    System.out.println("Waiting for Server...");
                    isPrinted = true;
                }
            }

        }

        System.out.println("The program terminated with no error and no exception");

    }

}
