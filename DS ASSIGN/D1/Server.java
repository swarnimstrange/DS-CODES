import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            AdditionService additionService = new AdditionServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AdditionService", additionService);
            System.out.println("Server is ready...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
