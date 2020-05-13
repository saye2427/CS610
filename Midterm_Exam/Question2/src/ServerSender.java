import java.io.*;
import java.net.*;

public class ServerSender implements Runnable {

    Socket clientSocket = null;
    PrintWriter writer = null; //We need a writer here because we are literally writing strings to send to the client

    //Constructor to pass in client socket so all server's messages can be sent to client
    public ServerSender(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream())); //writes message to send to client (via output stream)
            while(true) {
                String serverMessage = null;
                //Read user's input
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                serverMessage = userInput.readLine();
                writer.println(serverMessage); //writes message that the user entered to also be sent to the client
                writer.flush(); //if the PrintWriter is not flushed, for some reason, none of the server side's messages go through - flush must take place, and constantly, within the loop
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

}
