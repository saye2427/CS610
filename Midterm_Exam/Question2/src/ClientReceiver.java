import java.io.*;
import java.net.*;

public class ClientReceiver implements Runnable {

    Socket socket = null;
    BufferedReader reader = null; //Buffered reader will read the server's input (we need a reader here, because we are literally just reading the message sent from the server and printing it)

    //Constructor to pass in socket so all server's sent messages can be read
    public ClientReceiver(Socket socket) {
        this.socket = socket;
    }

    public void run() { //Method of Runnable
        try {
            reader = new BufferedReader(new InputStreamReader((socket.getInputStream()))); //read the input coming from server through the socket
            String message = null; //this MUST be declared as null prior to our loop (which ensures every message is read), otherwise the message is printed on the other side in an endless loop
            while(true) { //this loop will always run, allowing for constant check of new messages (i.e. multiple messages, flushes, etc)
                while((message = reader.readLine()) != null) {
                    System.out.println(message); //Prints server side input as it comes in

                    //Place this conditional after print so "end" prints on both sides as well
                    if(message.toUpperCase().equals("END")) //any variation of "end" will close the server (i.e. end, END, End, eND, etc.
                        break; //if the server entered "end", the client chat will close (as will the server chat) by breaking to clientSocket.close()
                }
                socket.close();
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

}
