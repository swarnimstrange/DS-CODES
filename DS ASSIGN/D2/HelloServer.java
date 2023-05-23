import org.omg.CORBA.*;
import HelloApp.*;

class HelloImpl extends HelloPOA {
  public String sayHello() {
    return "Hello, world!";
  }

  public void shutdown() {
    orb.shutdown(false);
  }
}

public class HelloServer {
  static ORB orb;

  public static void main(String args[]) {
    try {
      // Create and initialize the ORB
      orb = ORB.init(args, null);

      // Get the root POA and activate the POAManager
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();

      // Create a servant instance
      HelloImpl helloImpl = new HelloImpl();

      // Register the servant with the ORB
      org.omg.CORBA.Object ref = rootPOA.servant_to_reference(helloImpl);
      Hello href = HelloHelper.narrow(ref);

      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Bind the Hello object to the naming context
      NameComponent path[] = ncRef.to_name("Hello");
      ncRef.rebind(path, href);

      System.out.println("HelloServer ready and waiting...");

      // Wait for invocations from clients
      orb.run();
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}
