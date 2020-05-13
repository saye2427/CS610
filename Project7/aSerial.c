//File name: a.c

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//Returns a random number in the range -1 and 1.
double rand_double() {
    return 2 * (rand() / (double)RAND_MAX) -1;
}

int main() {
    double x, y, distance_squared, pi_estimate;
    int number_in_circle = 0, toss, number_of_tosses;
    printf("Enter number the total number of tosses: ");
    scanf("%d", &number_of_tosses);
    
    srand((long)time(NULL));
    
    for (toss = 0; toss < number_of_tosses; toss++) {
        x = rand_double();
        y = rand_double();
        distance_squared = x*x + y*y;
        
        if (distance_squared <= 1)
            number_in_circle++;
  }
  
  pi_estimate = 4 * number_in_circle / ((double)number_of_tosses);
  printf("Estimated pi = %f\n", pi_estimate);
  return 0; 
}
