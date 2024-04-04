package org.example;

/**
 * Class describing a process which includes an id, it´s
 * arrivalTime,
 * burst time - "Time to complete"
 * ,priority and remaining time
 */

public class Process implements Comparable<Process> {

    private int id;
    // Time of arrival for the process
    private int arrivalTime;
    // Time to complete the process
    private int burstTime;
    // Priority in a queue
    private int priority;
    // Remaining time = burstTime
    private int remainingTime;

    /**
     * Constructor for a process
     *
     * @param burstTime - Time to complete process
     * @param priority - Priority in a queue
     */
    public Process(int id,int arrivalTime, int burstTime, int priority){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    /**
     * Getter method for arrivalTime attribute
     *
     * @return - arrivalTime
     */
    public int getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Getter method for burstTime attribute
     *
     * @return - burstTime
     */
    public int getBurstTime() {
        return this.burstTime;
    }

    /**
     * Getter method for priority attribute
     *
     * @return - priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Getter method for remainingTime attribute
     *
     * @return - remainingTime
     */
    public int getRemainingTime() {
        return this.remainingTime;
    }

    /**
     * Setter method for remainingTime attribute
     *
     * @return - new remaining time
     */

    public int getId() {
        return this.id;
    }

    public int setRemainingTime(int remainingTime){
        return this.remainingTime = remainingTime;
    }

    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Process other) {
        //Higher priority processes should come first
        if(this.priority != other.getPriority()){
            return Integer.compare(this.getPriority(), other.getPriority());
        } else {
            //If priorities are equal, use arrival time
            return Integer.compare(this.arrivalTime, other.getArrivalTime());
        }
    }
}
