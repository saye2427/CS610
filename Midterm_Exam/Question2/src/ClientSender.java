import java.io.*;
import java.net.*;

public class ClientSender implements Runnable {

    Socket socket = null;
    PrintWriter writer = null; //We need a writer here because we are literally writing strings to send to the server

    //Constructor to pass in client socket so all server's messages can be sent to client
    public ClientSender(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            if(socket.isConnected()) {
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); //writes message to send to client (via output stream)
                String clientMessage = null;
                while(true) {
                    //Read user's input
                    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                    clientMessage = userInput.readLine();
                    writer.println(clientMessage); //writes message that the user entered to also be sent to the server
                    writer.flush(); //if the PrintWriter is not flushed, for some reason, none of the server side's messages go through - flush must take place, and constantly, within the loop
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

}
