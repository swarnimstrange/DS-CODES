import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdditionService extends Remote {
    int addNumbers(int num1, int num2) throws RemoteException;
}
