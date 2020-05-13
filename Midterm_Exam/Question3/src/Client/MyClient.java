package Client;

import Server.NamesInterface;

import java.util.*;
import java.net.MalformedURLException;
import java.rmi.*;

public class MyClient {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        String registryURL = "rmi://localhost:1234/names";
        NamesInterface ni = (NamesInterface) Naming.lookup(registryURL);
        Scanner keyboard = new Scanner(System.in); //Initialize scanner to read user inputs

        while(true){
            //Complete this while-loop
            System.out.print("Enter 1 to add a name, 2 to remove a name, 3 to list the names, or 4 to terminate: ");
            int userChoice = keyboard.nextInt();
            if (userChoice == 1) {

                System.out.print("Enter your first name: ");
                //Skip for Scanner nextLine issues
                keyboard.nextLine();
                //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                String fname = keyboard.nextLine();

                System.out.print("Enter your last name: ");
//                keyboard.nextLine(); //This line adds an extra newline that we don't need (i.e. need to press enter twice to see return message)
                String lname = keyboard.nextLine();

                System.out.println(ni.addName(fname, lname));

            } else if (userChoice == 2) {

                System.out.print("Enter your first name: ");
                keyboard.nextLine();
                String fname = keyboard.nextLine();

                System.out.print("Enter your last name: ");
//                keyboard.nextLine();
                String lname = keyboard.nextLine();

                System.out.println(ni.removeName(fname, lname));

            } else if (userChoice == 3) {

                System.out.print("Enter your last name: ");
                keyboard.nextLine();
                String lname = keyboard.nextLine();

                System.out.println(ni.listNames(lname));
                System.out.println(""); //To skip a line after lists of names are given as in sample dialogue

            } else if (userChoice == 4) {

                System.out.println("Goodbye.");
                break;

            } else {

                //Display and error message, and then prompt the user again (loop)
                System.out.println("Invalid input. Please try again");

            }

        }

    }

}
