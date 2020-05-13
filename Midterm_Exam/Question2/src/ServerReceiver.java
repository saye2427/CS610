import java.io.*;
import java.net.*;

public class ServerReceiver implements Runnable {

    Socket clientSocket = null;
    BufferedReader reader = null; //Buffered reader will read the client's input (we need a reader here, because we are literally just reading the message sent from the client and printing it)

    //Constructor to pass in client socket so all client's sent messages can be read
    public ServerReceiver(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() { //Method of Runnable
        try {
            reader = new BufferedReader(new InputStreamReader((clientSocket.getInputStream()))); //read the input coming from client through the socket
            String message = null; //this MUST be declared as null prior to our loop (which ensures every message is read), otherwise the message is printed on the other side in an endless loop
            while(true) { //this loop will always run, allowing for constant check of new messages (i.e. multiple messages, flushes, etc)
                while((message = reader.readLine()) != null) {
                    System.out.println(message); //Prints client side input as it comes in

                    //Place this conditional after print so "end" prints on both sides as well
                    if(message.toUpperCase().equals("END")) //any variation of "end" will close the server (i.e. end, END, End, eND, etc.
                        break; //if the client entered "end", the server chat will close (as will the client chat) by breaking to clientSocket.close()
                }
                clientSocket.close();
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

}
