//Project-5
//Example 9 in lecture 7 used as a reference

//STEP 1:
//Process 1 sends its number(i.e.19) to process 0. Process 0 receives this number and add to its number(i.e.8) to obtain 27. At the same time :
//Process 3 sends its number(i.e.15) to process 2. Process 2 receives this number and add to its number(i.e.7) to obtain 22. At the same time :
//Process 5 sends its number(i.e.13) to process 4. Process 4 receives this number and add to its number(i.e.7) to obtain 20. At the same time :
//Process 7 sends its number(i.e.14) to process 6. Process 6 receives this number and add to its number(i.e.12) to obtain 26.

//STEP 2:
//Process 2 sends its number (i.e. 22) to process 0. Process 0 receives this number and add to its number (i.e. 27) to obtain 49. At the same time:
//Process 6 sends its number(i.e.26) to process 4. Process 4 receives this number and add to its number(i.e.46) to obtain 49.

//STEP 3:
//Process 4 sends its number(i.e.46) to process 0. Process 0 receives this number and add to its number(i.e.49) to obtain 95.

//Note: Assume the user enters power of 2 for the numbers of processes.
//Note: In my solution I have a nested for-loop with an if-else if to do the major work and after this nested for-loop I have a simple if-statement to print the sum. It is ok if you do not follow my footsteps. However you must use MPI_Send and MPI_Recv.

//This may mean that we cannot use MPI_Reduce

//File name: a.c
#include <stdio.h>	
#include <stdlib.h>
#include <mpi.h> 
#include <time.h>

int main(){
  int sum, comm_sz, my_rank, i, next, value;

  MPI_Init(NULL, NULL); //Initiate all setup, i.e. assigning ranks to processes
  MPI_Comm_size(MPI_COMM_WORLD, &comm_sz); //comm_sz becomes the number of processes that we want to create (this and my_rank are provided/assigned at runtine)
  MPI_Comm_rank(MPI_COMM_WORLD, &my_rank); //obtains the rank of the running process (i.e. content of my_rank)
  srandom((unsigned)time(NULL) + my_rank);
  sum = random()%10;
  printf("Process %d generates: %d\n", my_rank, sum); //For checking/debugging
  for (next = 1; next < comm_sz; next *= 2)
    for(i = 0; i < comm_sz; i += 2*next)
      if(my_rank == next)  //Find what you need to replace for: ??. //These are the odd processes
      {
        //Complete this part.
        // printf("%d", sum);
        // printf(", ");
        MPI_Send(&sum, 1, MPI_INT, i, 0, MPI_COMM_WORLD); //(what is sent, size of sent item, mpi-type, send to rank, <varies based on application>, which group of processes we are sending to)
      }
      else 
        if(my_rank == i)	//Find what you need to replace for: ??.
      {  //Complete this part.
        // printf("%d", sum);
        // printf(", ");
        MPI_Recv(&sum, 1, MPI_INT, next, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE); //(what is received, length, mpi-type, rank of recipient, <same number for receiving process as is here in MPI_Send, informs the system the processes are of the same group, status of the processes)
        sum += sum;
        //MPI_Send(&sum, 1, MPI_INT, i, 0, MPI_COMM_WORLD); //Maybe we need a these even processes to be both receiving and sending(?)
      }

  if(my_rank == 0) //prints the value in rank 0 with each iteration (i.e. 128 processes prints 128 numbers and 1 sum)
    printf("\nSum = %d\n", sum);
  MPI_Finalize(); //cleans up the system and terminates all processes
  return 0;
}
