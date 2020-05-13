//File name: a.c

#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include <time.h>

int main(){
    int sum, comm_sz, my_rank, i, next, value;
    MPI_Init(NULL, NULL);
    MPI_Comm_size(MPI_COMM_WORLD, &comm_sz);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    srandom((unsigned)time(NULL) + my_rank);
    sum = random()%10;
   	// sum = my_rank;
    printf("%d,", sum);    

    for(next = 1; next < comm_sz; next *=2) {
        
        for(i = 0; i < comm_sz; i += 2*next) {
        
            if(my_rank == i) //check my_rank == i*even
   		    {
                int temp=sum;
                MPI_Recv(&sum, 1, MPI_INT, i+next, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
                sum += temp;

   		    }else
               if(my_rank == (i+next)) //check my_rank == i*odd
            {     
                MPI_Send(&sum, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
   		    }

   	    }

    }

    if(my_rank == 0)
        printf("\nSum = %d\n", sum);
    MPI_Finalize();
    return 0;

}
