package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class ClientHandler {

    private int id;

    public ClientHandler(){
        this.id = 1;
    }

    // Method to handle the client requests
    public Process handleClient(Socket clientSocket) {
        try {
            // Create a reader and writer for the client socket
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            boolean clientConnected = true;
            while (clientConnected) {
                // Read input from the client
                String input = reader.readLine().toUpperCase();
                try {
                    //Parse the input into numbers and an operator
                    String[] processParameters = input.split(" ");
                    int burstTime = Integer.parseInt(processParameters[1]);
                    int priority = Integer.parseInt(processParameters[2]);

                    Process process = new Process(id,burstTime, priority);

                    id++;
                    return process;
                    // Perform the operation and send the result to the clien
                } catch (NumberFormatException numberFormatException) {
                    // Handle invalid input
                    if (input.equalsIgnoreCase("exit")) {
                        writer.println("Disconnecting you from the server");
                        clientSocket.close();
                        clientConnected = false;
                    }
                    writer.println("Invalid format: Number + Number + Operator");

                }
                writer.flush();
            }} catch (IOException e) {
            System.out.println("main.java.Server exception: " + e.getMessage());
            e.printStackTrace();

        }
        return null;
    }
}
