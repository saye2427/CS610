//CS610 Final Exam, Question 1
//Monte Carlo Estimation of Pi using MPI

#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//Function to return a random number between -1 and 1
double rand_double() {
  return 2 * (rand() / (double)RAND_MAX) -1;
}

int main() {

    //Declare variables for pi estimation, based on serial program from project7
    double x, y, distance_squared, pi_estimation;
    long long int number_in_circle = 0; //Each thread will add its result to this variable
    long long int toss;
    long long int number_of_tosses;
    long long int total_in_circle;

    //Declare variables and initializations for MPI
    int comm_sz, my_rank;
    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &comm_sz);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    srand((unsigned)time(NULL) + my_rank);

    //Get number of tosses from user argument
    if (my_rank == 0) {
        printf("Enter the total number of tosses: ");
        fflush(stdout); //need to explicitly flush the standard output between printf and scanf, otherwise printf only prints after the user enters a number
        scanf("%lld", &number_of_tosses);
    }
    
    //Ensure number of tosses will divide equally amongst the number of processes
    if(number_of_tosses % comm_sz)
        number_of_tosses = ((number_of_tosses + comm_sz - 1)/comm_sz) * comm_sz;
    //Process 0 will get the number of tosses entered and bcast it to all other processes
    MPI_Bcast(&number_of_tosses, 1, MPI_LONG_LONG_INT, 0, MPI_COMM_WORLD);

    for(toss=0; toss < number_of_tosses/comm_sz; toss++) { //just as we did for project7 with dividing the number of tosses by the number of threads, we want to divide by comm_sz here
        x = rand_double();
        y = rand_double();
        distance_squared = x*x + y*y;
        if (distance_squared <= 1)
            number_in_circle++;
    }

    MPI_Reduce(&number_in_circle, &total_in_circle, 1, MPI_LONG_LONG_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    if(my_rank == 0) {
        pi_estimation = 4 * ((double)total_in_circle) / ((double)number_of_tosses);
        printf("Estimated pi = %f\n", pi_estimation);
    }

    MPI_Finalize();

    return 0;

}
