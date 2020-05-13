import java.io.*;
import java.net.*;
import java.util.*;

public class MyClientTest {
    public static void main(String[] args) {
        Socket clientSocket1 = null;
        Socket clientSocket2 = null;
        BufferedReader in1 = null;
        BufferedReader in2 = null;
        int ip1;
        int ip2;
        try {
            ip1 = 16790;
            ip2 = 16791;
            String host = "localhost";
            clientSocket1 = new Socket(host, ip1);
            clientSocket2 = new Socket(host, ip2);
            in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
            String s1 = in1.readLine();
            String s2 = in2.readLine();
            while (s1 == null || s2 == null) {    //As long as there is nothing in the stream keep reading
                s1 = in1.readLine();
                s2 = in2.readLine();
            }
//            System.out.println(s1);
//            System.out.println(s2);

            MyThread t1 = new MyThread();
            MyThread t2 = new MyThread();

            t1.start();
            t2.start();

            t1.getString(clientSocket1, s1);
            t2.getString(clientSocket2, s2);
//            System.out.println(t1.getString(clientSocket1, s1));
//            System.out.println(t2.getString(clientSocket2, s2));

            String s = t1.getString(clientSocket1, s1) + t2.getString(clientSocket2, s2);
//            System.out.print(s);
//            System.out.print("\n");

            StringTokenizer st = new StringTokenizer(s, " ");
            ArrayList<Integer> merged = new ArrayList<Integer>();
            while(st.hasMoreTokens()) {
                merged.add(Integer.parseInt(st.nextToken()));
            }

//            //Display unsorted Array
//            for(int i = 0; i < merged.size(); i++)
//                System.out.print(merged.get(i) + " ");

//            for(Integer i : merged )
//                System.out.print(i + " ");
//            System.out.print("\n");

            //Display sorted Array
            Collections.sort(merged);
            for(Integer i : merged )
                System.out.print(i + " ");

            in1.close();
            in2.close();
            clientSocket1.close();
            clientSocket2.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

}
