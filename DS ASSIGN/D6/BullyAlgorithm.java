import java.util.ArrayList;
import java.util.List;

class Processs implements Runnable {
    private final int processId;
    private final List<Integer> processes;
    private int coordinatorId;

    public Processs(int processId, List<Integer> processes) {
        this.processId = processId;
        this.processes = processes;
        this.coordinatorId = -1;
    }

    @Override
    public void run() {
        System.out.println("Process " + processId + " started.");

        // Check if the process is the coordinator
        if (coordinatorId == processId) {
            System.out.println("Process " + processId + " is the coordinator.");
        } else {
            // Process sends an election message to higher priority processes
            for (int higherId : getHigherPriorityProcesses()) {
                sendElectionMessage(higherId);
            }

            // Process waits for an answer from higher priority processes
            waitForAnswer();

            // If no answer received, the process becomes the coordinator
            if (coordinatorId == -1) {
                coordinatorId = processId;
                System.out.println("Process " + processId + " is the coordinator.");
                // Process sends a coordinator message to lower priority processes
                for (int lowerId : getLowerPriorityProcesses()) {
                    sendCoordinatorMessage(lowerId);
                }
            }
        }
    }

    private List<Integer> getHigherPriorityProcesses() {
        List<Integer> higherProcesses = new ArrayList<>();
        for (int id : processes) {
            if (id > processId) {
                higherProcesses.add(id);
            }
        }
        return higherProcesses;
    }

    private List<Integer> getLowerPriorityProcesses() {
        List<Integer> lowerProcesses = new ArrayList<>();
        for (int id : processes) {
            if (id < processId) {
                lowerProcesses.add(id);
            }
        }
        return lowerProcesses;
    }

    private void sendElectionMessage(int higherId) {
        System.out.println("Process " + processId + " sends election message to Process " + higherId);
        // Code to send an election message to higherId process
    }

    private void sendCoordinatorMessage(int lowerId) {
        System.out.println("Process " + processId + " sends coordinator message to Process " + lowerId);
        // Code to send a coordinator message to lowerId process
    }

    private void waitForAnswer() {
        // Simulating waiting for an answer
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BullyAlgorithm {
    public static void main(String[] args) {
        List<Integer> processes = new ArrayList<>();
        processes.add(1);
        processes.add(2);
        processes.add(3);
        processes.add(4);
        processes.add(5);

        // Create and start the processes
        Thread[] threads = new Thread[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            threads[i] = new Thread(new Processs(processes.get(i), processes));
            threads[i].start();
        }

        // Wait for all processes to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
