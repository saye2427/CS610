import java.io.*;
import java.net.*;
import java.util.*;

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
//            out1.println("From Port 16790: " + makeTheString());
//            out2.println("From Port 16791: " + makeTheString());
            out1.println(makeTheString());
            out2.println(makeTheString());
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

    public static String makeTheString() {
        final int n = 50;
        Random rand = new Random();
        int size = rand.nextInt(n) + 1;
        ArrayList<Integer> arr = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            arr.add(rand.nextInt(100) + 10);
        }
//        String arrString = arr.toString().replace(",", "").replace("[", "").replace("]", "").trim();
        StringBuffer arrString =  new StringBuffer();
        for (Integer s : arr) {
            arrString.append(s);
            arrString.append(" ");
        }
        String str = arrString.toString();

        return str;
    }

}
