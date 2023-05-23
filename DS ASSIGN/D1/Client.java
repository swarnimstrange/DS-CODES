import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AdditionService additionService = (AdditionService) registry.lookup("AdditionService");

            Scanner sc=new Scanner(System.in);

            System.out.println("Enter first number :");
            int num1=sc.nextInt();

            System.out.println("Enter second number :");
            int num2=sc.nextInt();

            int result = additionService.addNumbers(num1, num2);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

