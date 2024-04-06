# Assignment-3

## How to run
In this project the client-server model is used. The server runs the two different scheduling algorithms; 
preemptive priority scheduling and FIFO scheduling. The client's job is to send a process to the server.

### 1. Choose Scheduling Algorithm
Inside the 'Server' class, you can choose which scheduling algorithm to use by changing the 'schedulingAlgorithm' 
variable. The variable can be set to either preemptive priority scheduling (1) or FIFO scheduling (2).

### 2. Configure the Main class
In the 'Main' class, you can configure multiple parameters to choose how many processes to send to the server,
the maximum burst time, the maximum priority and the max delay on when the processes are sent.

### 3. Start the server
The server needs to be started before the client. Run the main method in the 'Server' class.

### 4. Start the Main class
Run the main method in the 'Main' class. This will send processes to the server according to the configuration
done in step 2.

### 5. Results
The results of the scheduling algorithms are printed in the server console. Including the average waiting time and turn
around time. They are printed each time a process finishes.


## Theory

### Preemptive Priority Scheduling
In a preemptive priority scheduling algorithm, the process with the highest priority is selected for execution.
If a new process arrives with a higher priority than the currently running process, the currently running process is 
preempted. If there are two processes with the same priority, the process that arrived first is selected.

### FIFO Scheduling
In a FIFO scheduling algorithm, the process that arrives first is selected for execution. 
Once that process is finished, the next process that arrived is selected.

### Arrival Time
The arrival time represents the time at which a process has arrived in an ongoing system.

### Burst Time
The burst time represents how much time it takes to execute a process.

### Priority
The priority represents the importance of a process. The higher the priority, the more important the process is.

### Turnaround Time
The turnaround time represents the total time a process has been in the system, including waiting time.
Turnaround time is calculated by subtracting the arrival time from the completion time.

### Waiting Time
The waiting time represents how long a process has been waiting in queue. It is calculated by subtracting the
arrival time from the turnaround time.

## Contributors
- Adrian Johansen
- Håkon Karlsen
- Matti Kjellstadli
- Di Xie
