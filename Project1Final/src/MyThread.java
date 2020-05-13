import java.io.*;
import java.net.*;

public class MyThread extends Thread {

    Socket clientSocket = null;
    String serverString = null;

    public String getString(Socket clientSocket, String serverString) {

        this.clientSocket = clientSocket;
        this.serverString = serverString;

        return serverString;

    }

}
