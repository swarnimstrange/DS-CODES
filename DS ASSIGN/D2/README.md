To create the "Hello World" CORBA example, you can follow the steps outlined below:

1. Define the Interface (Hello.idl):
   Create a file named `Hello.idl` and define the interface for the Hello object using IDL.
   ```idl
   module HelloApp {
     interface Hello {
       string sayHello();
       void shutdown();
     };
   };
   ```

2. Implement the Server (HelloServer.java):
   Create a file named `HelloServer.java` and implement the server-side code.
   ```java
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
   ```

3. Implement the Client Application (HelloClient.java):
   Create a file named `HelloClient.java` and implement the client-side code.
   ```java
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
   ```

4. Compile and Run the Application:
   Open the terminal, navigate to the directory where the `Hello.idl`, `HelloServer.java`, and `HelloClient.java` files are located, and follow the steps below:

   - Compile the IDL file using the `idlj` command:
     ```
     $ idlj -fall Hello.idl
     ```

   - Compile the server and client Java files along with the generated stubs and skeletons

:
     ```
     $ javac -cp . HelloApp/*.java HelloServer.java HelloClient.java
     ```

   - Run the server in one terminal window:
     ```
     $ java -cp . HelloServer
     ```

   - Run the client in another terminal window:
     ```
     $ java -cp . HelloClient
     ```

   You should see the client print the message received from the server, and the server should display the message "HelloServer ready and waiting..." indicating that it is ready to receive client invocations.

This example demonstrates the basic structure of a CORBA application with a server and a client, using the Hello World example. The server implements the Hello interface and registers the object with the naming service, while the client looks up the object and invokes its operations.