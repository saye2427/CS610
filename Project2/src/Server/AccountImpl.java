package Server;

import Client.AccountClient;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class AccountImpl extends UnicastRemoteObject implements AccountInterface {

    //Initialize ArrayList of Account object here
    ArrayList<AccountImpl> bank = new ArrayList<AccountImpl>();

    int index = 0;
    String name;
    int accountNo;
    float balance;

    public AccountImpl() throws RemoteException {
        super();
    }

    public AccountImpl(String name, int accountNo, float balance) throws RemoteException {
        super();
        this.name = name;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String createAccount(String name, float balance) throws RemoteException {
        /*
        In creating an account, it is the bank that selects an account number.
        In our project the first new account is 100, the next is 101, next is 102, ...
        Anyone can open a new account. It does not matter if the person already has accounts.
        */

        bank.add(index, new AccountImpl(name, index+100, balance));
        index++;

        return (bank.get(index-1).name + " your account number is: " + bank.get(index-1).accountNo);

    }

    public String getBalance(String name, int accountNo) throws RemoteException {
        /*
        If the name with accountNo is in the bank return the person's balance as a string.
        Otherwise either the name has no account or his accountNo is wrong.
        */

        int acctIndex = accountNo - 100;
        String message;

//        for(int i=0; i < bank.size() && bank.get(i) != null; i++) { //Because the acctNo is given, we do NOT need the for loop (which causes problems with return statements later)
        if (bank.get(acctIndex).name.equals(name) && bank.get(acctIndex).accountNo == accountNo) {
            //Return the current balance: "[name] your balance is: [balance]"
            message = (bank.get(acctIndex).name + " your balance is: " + String.valueOf(bank.get(acctIndex).balance));
        } else {
            message = "This name and accountNo combination does not exist. Please check for errors and try again.";
        }

        return message;
    }

    public String withdraw(String name, float amt, int accountNo) throws RemoteException {
        /* The account number with the name must be in the bank.
        If there are not enough funds, return a message to the user.
        If there are enough funds, withdraw and return the new balance.
        */

        int acctIndex = accountNo - 100;
        String message;

        //If there is an insufficient balance, return: "[name] you do not have enough funds to withdraw." in the form of a message
        //Else, return the new balance by subtracting [amt]: "Withdrawal successful. New balance: [balance]"
        if (!bank.get(acctIndex).name.equals(name) || bank.get(acctIndex).accountNo != accountNo) {
            message = "This name and accountNo combination does not exist. Please check for errors and try again.";
        } else if (amt > bank.get(acctIndex).balance) {
            message = (bank.get(acctIndex).name + " you do not have enough funds to withdraw.");
        } else {
            bank.get(acctIndex).balance = bank.get(acctIndex).balance - amt;
            message = ("Withdrawal successful. New balance: " + bank.get(acctIndex).balance);
        }

        return message;
    }

    public String deposit(String name, float amt, int accountNo) throws RemoteException {
        /*
        The account number with the name must be in the bank.
        If account number with the name is in the bank, deposit and return the new balance.
        */

        int acctIndex = accountNo - 100;
        String message;

        //If the account number and name do not exist in the bank, return a message that tells user so, and that he or she should create an account first.
        //Else, return the new balance by adding [amt]: "Deposit successful. New balance: [balance]"
        if (bank.get(acctIndex).name.equals(name) && bank.get(acctIndex).accountNo == accountNo) {
            bank.get(acctIndex).balance = bank.get(acctIndex).balance + amt;
            message = ("Deposit successful. New balance: " + bank.get(acctIndex).balance);
        } else {
            message = "This name and accountNo combination does not exist. Please check for errors and try again.";
        }

        //Needed to move around the if/else statements for the sake of control flow
//        if (!bank.get(acctIndex).name.equals(name) && bank.get(acctIndex).accountNo != accountNo) {
//            message = "This name and accountNo combination does not exist. Please check for errors and try again.";
//        } else {
//            bank.get(acctIndex).balance = bank.get(acctIndex).balance + amt;
//            message = ("Deposit successful. New balance: " + bank.get(acctIndex).balance);
//        }

        return message;

    }

    public String transfer(String name, int fromAccount, int toAccount, float amt) throws RemoteException {
        /*
        Both account numbers with the same name must be in the bank.
        If there are not enough funds in the account that is going to transfer, return a message to the user.
        If there are enough funds, withdraw and return the new balance.
        */

        int fromAcctIndex = fromAccount - 100;
        int toAcctIndex = toAccount - 100;
        String message;

        //If both account numbers and names do not exist in the bank, return a message that tells user so.
        //Else, if there are not enough funds in the account that is going to transfer, return a message to the user.
        //Else, return that the transfer was successful, and the amounts in both accounts
        if (bank.get(fromAcctIndex).name.equals(name) && bank.get(fromAcctIndex).accountNo == fromAccount && bank.get(toAcctIndex).name.equals(name) && bank.get(toAcctIndex).accountNo == toAccount && bank.get(fromAcctIndex).balance >= amt) {
            bank.get(fromAcctIndex).balance = bank.get(fromAcctIndex).balance - amt;
            bank.get(toAcctIndex).balance = bank.get(toAcctIndex).balance + amt;
            message = ("Transfer successful. \nNew balance in account " + bank.get(fromAcctIndex).accountNo + ": " + bank.get(fromAcctIndex).balance + "\nNew balance in account " + bank.get(toAcctIndex).accountNo + ": " + bank.get(toAcctIndex).balance);
        } else if (bank.get(fromAcctIndex).balance < amt) {
            message = ("Insufficient funds for transfer in origin account.");
        } else {
            message = "This name and accountNo combinations do not exist. Please check for errors and try again.";
        }

        //Needed to move around the if/else statements for the sake of control flow
//        if (!bank.get(fromAcctIndex).name.equals(name) && bank.get(fromAcctIndex).accountNo != fromAccount && !bank.get(toAcctIndex).name.equals(name) && bank.get(toAcctIndex).accountNo != toAccount) {
//            message = "This name and accountNo combinations do not exist. Please check for errors and try again.";
//        } else if (bank.get(fromAcctIndex).balance < amt) {
//            message = ("Insufficient funds for transfer in origin account.");
//        } else {
//            bank.get(fromAcctIndex).balance = bank.get(fromAcctIndex).balance - amt;
//            bank.get(toAcctIndex).balance = bank.get(toAcctIndex).balance + amt;
//            message = ("Transfer successful. \nNew balance in account " + bank.get(fromAcctIndex).accountNo + ": " + bank.get(fromAcctIndex).balance + "\nNew balance in account " + bank.get(toAcctIndex).accountNo + ": " + bank.get(toAcctIndex).balance);
//        }

        return message;
    }

}
