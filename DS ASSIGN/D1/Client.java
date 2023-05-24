import java.rmi.Naming;

public class AddClient {
    public static void main(String[] args) {
        try {
            String serverURL = "rmi://" + args[0] + "/AddServer";
            AddServerIntf server = (AddServerIntf) Naming.lookup(serverURL);

            double num1 = Double.parseDouble(args[1]);
            double num2 = Double.parseDouble(args[2]);

            double result = server.add(num1, num2);
            System.out.println("Sum: " + result);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


