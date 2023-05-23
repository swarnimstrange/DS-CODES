import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdditionServiceImpl extends UnicastRemoteObject implements AdditionService
{
    protected AdditionServiceImpl() throws RemoteException
    {
        super();
    }

    @Override
    public int addNumbers(int num1, int num2) throws RemoteException
    {
        return num1+num2;
    }
}