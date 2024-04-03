package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();
        Client client = new Client();
        while (true){
            client.sendProcess("Request: " + rand.nextInt(1,10)+ " " + rand.nextInt(1,10));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}