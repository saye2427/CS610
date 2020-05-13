package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountInterface extends Remote {

    // In creating an account, it is the bank that selects an account number.
    // In our project the first new account is 100, the next is 101, next is 102, ...
    // Anyone can open a new account. It does not matter if the person already has accounts.
    public String createAccount(String name, float balance) throws RemoteException;

    // If the name with accountNo is in the bank return the person's balance as a string.
    // Otherwise either the name has no account or his accountNo is wrong.
    public String getBalance(String name, int accountNo) throws RemoteException;

    // The account number with the name must be in the bank.
    // If there are not enough funds, return a message to the user.
    // If there are enough funds, withdraw and return the new balance.
    public String withdraw(String name, float amt, int accountNo) throws RemoteException;

    // The account number with the name must be in the bank.
    // If account number with the name is in the bank, deposit and return the new balance.
    public String deposit(String name, float amt, int accountNo) throws RemoteException;

    // Both account numbers with the same name must be in the bank.
    // If there are not enough funds in the account that is going to transfer, return a message to the user.
    // If there are enough funds, withdraw and return the new balance.
    public String transfer(String name, int fromAccount, int toAccount, float amt) throws RemoteException;

}
