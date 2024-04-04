# Assignment-3

## How to run
In this project the client-server model is used. The server runs the two different scheduling algorithms; 
preemptive priority scheduling and FIFO scheduling. The client's job is to send processes to the server.

### 1. Choose Scheduling Algorithm
Inside the 'Server' class, you can choose which scheduling algorithm to use by changing the 'schedulingAlgorithm' 
variable. The variable can be set to either preemptive priority scheduling (1) or FIFO scheduling (2).

### 2. Start the server
The server needs to be started before the client. Run the main method in the 'Server' class.

### 3. Start the UserRunner
HER SKAL DET STÅ HVA USERRUNNER GJØR!

### 4. Results
The results of the scheduling algorithms are printed in the server console. Including the average waiting time and turn
around time.


## Theory

### Preemptive Priority Scheduling
In a preemptive priority scheduling algorithm, the process with the highest priority is selected for execution.
If a new process arrives with a higher priority than the currently running process, the currently running process is 
preempted. If there are two processes with the same priority, the process that arrived first is selected.

### FIFO Scheduling
In a FIFO scheduling algorithm, the process that arrives first is selected for execution. 
Once that process is finished, the next process that arrived is selected.


## Contributors
- Adrian Johansen
- Håkon Karlsen
- Matti Kjellstadli
- Di Xie
