import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddServer extends UnicastRemoteObject implements AddServerIntf {
    public AddServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            AddServer server = new AddServer();
            Naming.rebind("AddServer", server);
            System.out.println("Server started.");

            // Start a new thread for each client request
            while (true) {
                new ClientHandler(server).start();
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private AddServerIntf server;

    public ClientHandler(AddServerIntf server) {
        this.server = server;
    }

    public void run() {
        try {
            // Process the client request
            // In this case, we are adding 10 and 20
            double result = server.add(10, 20);

            // Display the intermediate sum calculated by each thread
            System.out.println("Intermediate sum: " + result);
        } catch (Exception e) {
            System.err.println("Exception in client handler: " + e.toString());
            e.printStackTrace();
        }
    }
}
