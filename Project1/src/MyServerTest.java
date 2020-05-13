import java.io.*;
import java.net.*;
import java.util.*;

public class MyServerTest {
    public static void main(String[] args){

        final int SIZE = 50;
        Random rand = new Random();
        int size = rand.nextInt(SIZE) + 1;
        ArrayList<Integer> a = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++)
            a.add(rand.nextInt(100) + 10);
        //Collections.sort(a);
        //for (Integer i : a)
        //    System.out.print(i + ", ");
        //System.out.println("\n-------------------------------------");
        int sizeB = rand.nextInt(SIZE) + 1;
        ArrayList<Integer> b = new ArrayList<Integer>(sizeB);
        for(int i = 0; i < sizeB; i++)
            b.add(rand.nextInt(100) + 10);
        //Collections.sort(b);
        //for (Integer i : b)
        //    System.out.print(i + ", ");

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
            out1.println("From Port 16790: " + a.toString());
            out2.println("From Port 16791: " + b.toString());
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
