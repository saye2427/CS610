//CS610 Final Exam, Question 3
//Monte Carlo Estimation of Pi using OpenMP

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>
#include <time.h>

double rand_double() {
   return 2 * (rand() / (double)RAND_MAX) - 1;
}

int main() {
    long long int total_number_of_tosses, number_in_circle;
    int thread_count;
    int i;
    double x, y, distance;
    
    printf("Enter number of threads and the total number of tosses: ");
    scanf("%d%lld", &thread_count, &total_number_of_tosses);
    
    number_in_circle = 0;
    // srandom(0);
    srand(time(NULL));
    
    #pragma omp parallel for num_threads(thread_count) \
        reduction(+: number_in_circle) private(x, y, distance)
        
    for (i = 0; i < total_number_of_tosses; i++) {
        x = rand_double(); // -1 to 1
        y = rand_double();
        distance = x*x + y*y;

  	    if (distance <= 1) {
            number_in_circle += 1;
  	    }
    }
    
    double pi = 4*number_in_circle/((double) total_number_of_tosses);
    printf("Estimate of Pi = %f\n", pi);
    
    return 0;

}  
