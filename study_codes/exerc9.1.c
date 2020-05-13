//File name: a.c
#include <stdio.h>
#include <omp.h> 

int main() {
   long long n = 1000000000, k;
   int thread_count = 1000, factor = 1;
   long long x = 1;

#pragma omp parallel for num_threads(thread_count) reduction(*: x)
  for (k = 0; k < n; k++) {
    factor = -factor; 
    x *= factor;
  }

  printf("With n = %lld and %d threads, the sum = %lld\n", n, thread_count, x);
  return 0;
}
