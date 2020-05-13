package Server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class AccountServer {

    public static void main(String args[]) {

        try {

            int port = 16790;
            String host = "localhost";
            AccountImpl exportedObj = new AccountImpl();
            LocateRegistry.createRegistry(port);
            String registryURL = "rmi://" + host + ":" + port + "/Server.Accounts";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Server ready.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
