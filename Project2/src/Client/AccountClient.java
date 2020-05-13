package Client;

import Server.AccountInterface;

import java.rmi.*;
import java.util.*;

public class AccountClient {

    public static void main(String args[]) {

        //Initialize Scanner to read user inputs
        Scanner scan = new Scanner(System.in);

        try {

            //Initialize same port number and host here as server to establish connection
            int port = 16790;
            String host = "localhost";
            String registryURL = "rmi://" + host + ":" + port + "/Server.Accounts";
            Server.AccountInterface ai = (AccountInterface)Naming.lookup(registryURL); //Reference must be made to SERVER'S AcctInterface here, because the client's proxy will contact the Server's Interface (via the server proxy) for information
//            System.out.println("Lookup completed."); //test that client can locate registry
//            System.out.println("");

            //Prompt user to enter 1 to create, 2 to get balance, 3 to withdraw, 4 to deposit, 5 to transfer, or 6 to terminate
            //Wait for the chosen option, and given the choice, carry out the correct corresponding function from AccountImpl
            //Then loop back to prompting for the next action, until 6 is chosen (which terminates everything (and closes the client/server)
            boolean valid = true;
            //System.out.println(userChoice); //Test

            while(valid) {

                System.out.println("Enter 1 to create, 2 to get balance, 3 to withdraw, 4 to deposit, 5 to transfer, or 6 to terminate");
                int userChoice = scan.nextInt();

                if (userChoice == 1) {

                    //Prompt the user to "Enter the name of the person: "
                    System.out.print("Enter the name of the person: ");
                    //Skip for Scanner nextLine issues
                    scan.nextLine();
                    //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                    String name = scan.nextLine();
//                    System.out.println(name);
                    //Prompt the user to "Enter the amount: "
                    System.out.print("Enter the amount: ");
                    float balance = scan.nextFloat();

                    System.out.println(ai.createAccount(name, balance));

                } else if (userChoice == 2) {

                    //Prompt the user to "Enter the name of the person: "
                    System.out.print("Enter the name of the person: ");
                    //Skip for Scanner nextLine issues
                    scan.nextLine();
                    //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                    String name = scan.nextLine();
//                    System.out.println(name);
                    //Prompt the user to "Enter the account number: "
                    System.out.print("Enter the account number: ");
                    int accountNo = scan.nextInt();

                    System.out.println(ai.getBalance(name, accountNo));

                } else if (userChoice == 3) {

                    //Prompt the user to "Enter the name of the person: "
                    System.out.print("Enter the name of the person: ");
                    //Skip for Scanner nextLine issues
                    scan.nextLine();
                    //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                    String name = scan.nextLine();
                    //Prompt the user to "Enter the amount: "
                    System.out.print("Enter the amount: ");
                    float amt = scan.nextFloat();
                    //Prompt the user to "Enter the account number: "
                    System.out.print("Enter the account number: ");
                    int accountNo = scan.nextInt();

                    System.out.println(ai.withdraw(name, amt, accountNo));

                } else if (userChoice == 4) {

                    //Prompt the user to "Enter the name of the person: "
                    System.out.print("Enter the name of the person: ");
                    //Skip for Scanner nextLine issues
                    scan.nextLine();
                    //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                    String name = scan.nextLine();
                    //Prompt the user to "Enter the amount: "
                    System.out.print("Enter the amount: ");
                    float amt = scan.nextFloat();
                    //Prompt the user to "Enter the account number: "
                    System.out.print("Enter the account number: ");
                    int accountNo = scan.nextInt();

                    System.out.println(ai.deposit(name, amt, accountNo));

                } else if (userChoice == 5) {

                    //Prompt the user to "Enter the name of the person: "
                    System.out.print("Enter the name of the person: ");
                    //Skip for Scanner nextLine issues
                    scan.nextLine();
                    //Skip the newline so the scanner waits for the String answer before moving to the next prompt
                    String name = scan.nextLine();
                    //Prompt the user to "Enter the amount: "
                    System.out.print("Enter the amount: ");
                    float amt = scan.nextFloat();
                    //Prompt the user to "Enter the account number to take from: "
                    System.out.print("Enter the account number to take from: ");
                    int originAcctNo = scan.nextInt();
                    //Prompt the user to "Enter the account number to transfer to: "
                    System.out.print("Enter the account number to transfer to: ");
                    int targetAcctNo = scan.nextInt();

                    System.out.println(ai.transfer(name, originAcctNo, targetAcctNo, amt));

                } else if (userChoice == 6) {

                    System.out.println("Goodbye.");
                    valid = false;

                } else {
                    //Display and error message, and then prompt the user again (loop)
                    System.out.println("Invalid input. Please try again");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
