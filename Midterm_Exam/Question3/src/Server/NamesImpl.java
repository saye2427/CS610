package Server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class NamesImpl extends UnicastRemoteObject implements NamesInterface {
    //Complete this class
    //Initialize ArrayList of NamesImpl type here
    ArrayList<NamesImpl> registry = new ArrayList<NamesImpl>();

    String fname;
    String lname;

    public NamesImpl() throws RemoteException {
        super();
    }

    public NamesImpl(String fname, String lname) throws RemoteException {
        super();
        this.fname = fname;
        this.lname = lname;
    }

    public String addName(String firstName, String lastName) throws RemoteException {
        //Complete this method
        /* Method: addName
         * To add a first name and last name to the list of names.
         * If the name is not in the list add it and inform the client:
         * I included the name.
         * If the name is in the list inform the client:
         * Your name is already in the list.
         */

        String message = null;
        boolean found = false; //added boolean to provide [external] conditions for return statements, since the message cannot be updated within the loop

        //Check if the name is in the list
        for(int i=0; i < registry.size() && found == false; i++) {
            if(registry.get(i).fname.equals(firstName) && registry.get(i).lname.equals(lastName))
                found = true;
        }

        if (found == false) {
            registry.add(new NamesImpl(firstName, lastName));
            message = "I included the name.";
        } else {
            message = "Your name is already in the list.";
        }

        return message;

    }

    public String removeName(String firstName, String lastName) throws RemoteException {
        //Complete this method
        /* Method: removeName
         * To remove a first name and last name from the list of names.
         * If the name is not in the list inform the client:
         * No such name in the list.
         * If the name is in the list remove it and inform the client:
         * Your name is removed from the list.
         */

        String message = null;
        boolean found = false; //added boolean to provide [external] conditions for return statements, since the message cannot be updated within the loop

        //Check if the name is in the list
        for(int i=0; i < registry.size() && found == false; i++) {
            if(registry.get(i).fname.equals(firstName) && registry.get(i).lname.equals(lastName)) {
                registry.remove(i); //The removal needs to take place within the loop; an external variable will again, not be updated
                found = true; //to break out of the loop to if/else
            }
        }

        if (found == false) {
            message = "No such name in the list.";
        } else {
            message = "Your name is removed from the list.";
        }

        return message;

    }

    public String listNames(String lastName) throws RemoteException {
        //Complete this method
        /* Method: listNames
         * To list all the names with the last name entered (i.e. they have the same last name).
         * If the name is not in the list inform the client:
         * There no one with this last name in the list.
         * If the name is in the list send the client: the list of all the
         * names (first name and last name) of people with this name
         * N.B.: There is no need to sort the names; they can be listed as they appear in the registry/arrayList
         */

        String message;
        boolean found = false;

        //Check if the name is in the list
        for(int i=0; i < registry.size() && found == false; i++) {
            if(registry.get(i).lname.equals(lastName))
                found = true; //break out of the loop [to else] if even one name with the last name entered is found
        }

        if (found == false) {
            message = "There is no one with this last name in the list.";
        } else {
            //Create a new ArrayList containing just the names with the given last name
            ArrayList<NamesImpl> likeNames = new ArrayList<NamesImpl>();
            for(int i=0; i < registry.size(); i++) {
                if(registry.get(i).lname.equals(lastName)) {
                    likeNames.add(registry.get(i));
                }
            }

            //Put the names in the ArrayList into an array (since we now know the size, and can easily convert an array to a string)
            String[] namesList = new String[likeNames.size()];
            for(int i=0; i < namesList.length; i++) {
                namesList[i] = (likeNames.get(i).fname + " " + likeNames.get(i).lname);
            }

            //Clean up the string as needed to produce the desired output - APIs galore!
            String arrayStr = Arrays.toString(namesList).replace(", ", "\n").replace("[", "").replace("]", "").trim();
            message = arrayStr;

        }

        return message;

    }

}
