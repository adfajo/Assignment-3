package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();

        // Amount of requests to send to the server.
        int requestAmount = 5;
        // Max delay for the client to send a request, the minimum is 1 second.
        // Time is randomized between 1 and maxDelay seconds.
        int maxDelay = 6;
        // Max burst time and priority.
        int maxBurstTime = 10;
        int maxPriority = 10;

        while (requestAmount != 0){
            Client client = new Client();
            client.sendProcess("Request: " + rand.nextInt(1,maxBurstTime)+ " " + rand.nextInt(1,maxPriority));
            requestAmount--;
            try {
                Thread.sleep(rand.nextInt(1000,maxDelay*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}