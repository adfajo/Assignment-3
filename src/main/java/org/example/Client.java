package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    // BufferedReader to read data from the server
    private BufferedReader reader;
    // PrintWriter to send data to the server
    private PrintWriter writer;
    // Socket to connect to the server
    private Socket socket;

    public Client() {
        try {
            // Connect to the server on localhost on port 1234
            socket = new Socket("localhost", 1234);
            System.out.println("Connected to the server");
            // Initialize the reader and writer with the socket's input and output streams
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("main.java.Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public String sendProcess(String command){
        String response = "Invalid Command";
        try {
            // Send the command to the server
            writer.println(command);
            writer.flush();
            // Read the server's response
            response = reader.readLine();
        } catch (IOException e) {
            System.out.println("main.java.Client exception: " + e.getMessage());
            e.printStackTrace();
            return ("Invalid Command");
        }
        return "Server replied: " + response;
    }

    public String requestAverageWaitTime() {
        return sendProcess("REQUEST_AVERAGE_WAIT_TIME");
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}