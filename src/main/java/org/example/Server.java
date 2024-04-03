package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Server {
    //Variable to store the total elapsed time
    private long totalElapsedTime;
    // ServerSocket to listen for client connections
    private ServerSocket serverSocket;
    // Variable to store the number of threads
    private int threadCount;
    // List to store the client sockets
    private ArrayList<Process> clientSockets;

    //Constructor to initialize the server socket and client socket list
    public void Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSockets = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start the server and handle client connections.
    public void start() {
        System.out.println("Server started.");
        ClientHandler clientHandler = new ClientHandler();
        while (true) {
            this.priorityScheduling();
            try {
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                //TODO: Change this to a nanotime counter

                // Record the start time
                long startTime = System.currentTimeMillis();

                //TODO: Fjern clients fra lista etterhvert

                // Add the client socket to the list and start a new thread to handle the client
                clientSockets.add(clientHandler.handleClient(clientSocket));

                // Record the end time and calculate the elapsed time
                long endTime = System.currentTimeMillis();
                long elapsedTimeMs = endTime - startTime;
                totalElapsedTime += elapsedTimeMs;
                threadCount++;
                System.out.println("Time to process " + threadCount + " threads: " + totalElapsedTime + " ms");

            } catch (IOException e) {
                System.out.println("Catch");
                throw new RuntimeException(e);
            }


        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.Server(1234);
        server.start();
    }

    public void priorityScheduling() {
        System.out.println("helloooooo");
        PriorityQueue<Process> queue = new PriorityQueue<>();

        int currentTime = 0;

        for (Process process : clientSockets) {
            if (process.getArrivalTime() <= currentTime && process.getRemainingTime() > 0) {
                queue.add(process);
            }
        }

        if (!queue.isEmpty()) {
            Process runningProcess = queue.poll();
            System.out.println("Process " + runningProcess.getId() + " is running at time " + currentTime);
            runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);

            if (runningProcess.getRemainingTime() == 0) {
                System.out.println("Process " + runningProcess.getId() + " completed at time " + (currentTime + 1));
                clientSockets.remove(runningProcess);
            }
        } else {
            System.out.println("CPU is idle at time " + currentTime);
        }
    }
}

