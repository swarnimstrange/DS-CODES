import org.omg.CORBA.*;
import HelloApp.*;

public class HelloClient {
  public static void main(String args[]) {
    try {
      // Create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Lookup the Hello object in the naming context
      Hello hello = HelloHelper.narrow(ncRef.resolve_str("Hello"));

      // Invoke the sayHello() operation and print the result
      String result = hello.sayHello();
      System.out.println("Server says: " + result);

      // Invoke the shutdown() operation
      hello.shutdown();
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}
