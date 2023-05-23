To develop a distributed system in Java using MPJ Express for finding the sum of N elements in an array by distributing the workload among multiple processors, you can follow the steps below:

1. Install MPJ Express:
   - Download MPJ Express (mpj.jar) from the official website and unpack it.
   - Set the MPJ_HOME and PATH environment variables. For example:
     ```
     export MPJ_HOME=/path/to/mpj/
     export PATH=$MPJ_HOME/bin:$PATH
     ```

2. Write the Java code:
   Create a file named `SumCalculator.java` and add the following code:

```java
import mpi.*;

public class SumCalculator {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // The array to be summed
        int n = array.length; // Total number of elements
        int localSum = 0; // Partial sum for each processor

        // Divide the array among processors
        int localSize = n / size; // Number of elements assigned to each processor
        int[] localArray = new int[localSize]; // Local array for each processor

        // Scatter the array to each processor
        MPI.COMM_WORLD.Scatter(array, 0, localSize, MPI.INT, localArray, 0, localSize, MPI.INT, 0);

        // Calculate the partial sum for each processor
        for (int i = 0; i < localSize; i++) {
            localSum += localArray[i];
        }

        // Gather the partial sums to the root processor
        int[] allSums = null;
        if (rank == 0) {
            allSums = new int[size];
        }

        MPI.COMM_WORLD.Gather(new int[]{localSum}, 0, 1, MPI.INT, allSums, 0, 1, MPI.INT, 0);

        // Display the intermediate sums calculated by each processor
        if (rank == 0) {
            for (int i = 0; i < size; i++) {
                System.out.println("Intermediate Sum at Processor " + i + ": " + allSums[i]);
            }
        }

        // Calculate the final sum by reducing the intermediate sums
        int[] finalSumArr = new int[1];
        MPI.COMM_WORLD.Reduce(new int[]{localSum}, 0, finalSumArr, 0, 1, MPI.INT, MPI.SUM, 0);

        // Display the final sum at the root processor
        if (rank == 0) {
            int finalSum = finalSumArr[0];
            System.out.println("Final Sum: " + finalSum);
        }

        MPI.Finalize();
    }
}
```

3. Compile the program:
   Open the terminal, navigate to the directory where `SumCalculator.java` is saved, and compile the program using the MPJ Express compiler:
   ```
   $ javac -cp /path/to/mpj.jar SumCalculator.java
   ```

4. Running MPJ Express in Multi-core Configuration:
   Open the terminal, navigate to the directory where `SumCalculator.class` and `mpj.jar` are located, and run the program using the MPJ Express launcher:
   ```
   $ mpjrun.sh -np <number_of_processors> SumCalculator
   ```
   Replace `<number_of_processors>` with the desired number of processors or cores you want to use