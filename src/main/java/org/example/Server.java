package org.example;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
  //Variable to store the total elapsed time
  private long totalElapsedTime;
  // ServerSocket to listen for client connections
  private ServerSocket serverSocket;
  // Variable to store the number of threads
  private int threadCount;
  // List to store the client sockets
  private ArrayList<Process> clientSockets;
  private ExecutorService scheduler;
  private int currentTime;
  private int schedulingType;
  private long totalTurnAroundTime;
  private int processCount;
  private long totalWaitingTime;

  //Constructor to initialize the server socket and client socket list
  public void Server(int port) {
    try {
      this.serverSocket = new ServerSocket(port);
      this.clientSockets = new ArrayList<>();
      this.scheduler = Executors.newSingleThreadExecutor();
      //Scheduling types:
      // 1 = Preemptive Priority Scheduling,
      // 2 = First Come First Serve.
      this.schedulingType = 1;
      currentTime = 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Method to start the server and handle client connections.
  public void start() {
    System.out.println("Server started.");
    ClientHandler clientHandler = new ClientHandler();

    // Priority scheduling in a separate thread to ensure constant running of method
    scheduler.submit(() -> {
      while (true) {
        if(this.schedulingType == 1){
          priorityScheduling();
        }else if(this.schedulingType == 2){
          this.fifoScheduling();
        }
        currentTime++;
        try {
          Thread.sleep(1000); // Sleep for 1 second (Simulation speed)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    // Main server loop for accepting client connections
    while (true) {
      try {
        // Accept a client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Process added: " + clientSocket.getInetAddress());

        //TODO: Change this to a nanotime counter

        // Record the start time
        long startTime = System.currentTimeMillis();

        //TODO: Fjern clients fra lista etterhvert

        // Add the client socket to the list and start a new thread to handle the client
        clientSockets.add(clientHandler.handleClient(clientSocket,currentTime));

        // Record the end time and calculate the elapsed time
        long endTime = System.currentTimeMillis();
        long elapsedTimeMs = endTime - startTime;
        totalElapsedTime += elapsedTimeMs;
        threadCount++;

        // Closing the connection to allow for further requests
        clientSocket.close();

      } catch (IOException e) {
        System.out.println("Catch");
        throw new RuntimeException(e);
      }
    }
  }

  private void fifoScheduling() {
    ArrayList<Process> queue = new ArrayList<>();

    for(Process process : clientSockets){
      if(process.getArrivalTime() <= currentTime && process.getRemainingTime() > 0) {
        queue.add(process);
      }
    }

    if(!queue.isEmpty()){
      Process runningProcess = queue.get(0);
        System.out.println("Process " + runningProcess.getId() + " is running at time " + currentTime);
        runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
        if(runningProcess.getRemainingTime() == 0){
          System.out.println("Process " + runningProcess.getId() + " completed at time " + (currentTime + 1));
          clientSockets.remove(runningProcess);
          totalTurnAroundTime += currentTime - runningProcess.getArrivalTime();
          processCount++;

          //Print the average waiting time
          System.out.println("Average waiting time is: " + getAverageWaitingTime());
        }
    } else{
        System.out.println("CPU is idle at time " + currentTime);
    }
  }

  public static void main(String[] args) {
    Server server = new Server();
    server.Server(1234);
    server.start();
  }

  public void priorityScheduling() {
    // Priority queue to store the processes
    PriorityQueue<Process> queue = new PriorityQueue<>();

    // Add the processes to the queue
    for (Process process : clientSockets) {
      if (process.getArrivalTime() <= currentTime && process.getRemainingTime() > 0) {
        queue.add(process);
      }
    }

    // Run the process with the highest priority
    if (!queue.isEmpty()) {
      Process runningProcess = queue.poll();
      System.out.println(
          "Process " + runningProcess.getId() + " is running at time " + currentTime);
      runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
      totalWaitingTime += clientSockets.size() - 1;

        // Remove the process if it is completed
      if (runningProcess.getRemainingTime() == 0) {
        System.out.println("Arrival time: " + runningProcess.getArrivalTime() + " Finished time" + currentTime);
        System.out.println(
            "Process " + runningProcess.getId() + " completed at time " + (currentTime + 1));
        clientSockets.remove(runningProcess);
        totalTurnAroundTime += (currentTime - runningProcess.getArrivalTime());
        processCount++;

        System.out.println("Average waiting time is: " + getAverageWaitingTime());
        System.out.println("Average turnaround time is: " + getAverageTurnaroundTime());
      }
    } else {
      System.out.println("CPU is idle at time " + currentTime);
    }
  }

  public double getAverageWaitingTime(){
    return (double) totalWaitingTime / processCount;
  }

  public void setSchedulingType(int schedulingType){
    this.schedulingType = schedulingType;
  }
}