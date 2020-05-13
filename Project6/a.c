//File name: a.c

#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int comm_sz, my_rank, i, j, n;
    int *send_vector = NULL, *receive_vector;

    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &comm_sz);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    srandom((unsigned)time(NULL) + my_rank); //"seed", makes random work
    //n = (random()%10 + 1)*comm_sz; //To make it divisible by comm_sz. Each process has its own n.

    if (my_rank == 0)
    {
        n = (random() % 10 + 1) * comm_sz; //If this is process 0, generate a random for 
        // printf("n=%d\n", n);

        send_vector = malloc(n * sizeof(int));
        for (i = 0; i < n; i++)
        {
            send_vector[i] = random() % 10;
            printf("%d, ", send_vector[i]);
        }
        printf("\n");
    };

    MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD); //process 0 will broadcast n to other process

    j = n / comm_sz; //Each process has its own j
    receive_vector = malloc(j * sizeof(int)); //Each process creates its own array
    MPI_Scatter(send_vector, j, MPI_INT, receive_vector, j, MPI_INT, 0, MPI_COMM_WORLD);
    for (i = 0; i < j; i++)
        printf("rank %d: receive_vector[%d] = %d\n", my_rank, i, -receive_vector[i]);

    MPI_Finalize();
    return 0;
}
