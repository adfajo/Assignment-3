package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();


        while (true){
            new Client().sendProcess("Request: " + rand.nextInt(1,10)+ " " + rand.nextInt(1,10));
            try {
                Thread.sleep(rand.nextInt(1000,10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}