//File name: a.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>


int *a, *b, *c;
int n;

void Merge(int a[], int b[], int c[]);
void Sort(int* x);
void *Thread_Sort (void* thread);

int main(){

long thread; //Use as a control variable in a for-loop.
pthread_t* thread_handles;

//pthread_t thread_handles[2];

int thread_count = 2;

int i;
srand(time(0));
n = rand()%101 + 50;
a =(int *)malloc(n*sizeof(int));
b =(int *)malloc(n*sizeof(int));
c =(int *)malloc(2*n*sizeof(int));
  for(i = 0; i < n; i++){
 	a[i] = rand()%100;
 	b[i] = rand()%100;
  }

thread_handles = malloc(thread_count*sizeof(pthread_t));
    
//pthread_create(&thread_handles[0], NULL, Sort, a);
//pthread_create(&thread_handles[1], NULL, Sort, b);

for (thread = 0; thread < thread_count; thread++)
	pthread_create(&thread_handles[thread], NULL, Thread_Sort, (void*) thread);

for (thread = 0; thread < thread_count; thread++)
	pthread_join(thread_handles[thread], NULL);
  free(thread_handles);

/* Checking if the arrays are sorted

for(i = 0; i < n; i++)
	printf("%d, ", a[i]);
  printf("\n^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

for(i = 0; i < n; i++)
	printf("%d, ", b[i]);
  printf("\n^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
*/

Merge(a, b, c);
  return 0;
}

///////////////////// functions

void *Thread_Sort (void* thread){


long My_Thread = (long) thread;

if (My_Thread == 0)
 Sort(a);
else
 Sort(b);

return NULL;

}


int findIndexOfSmallest(int a[], int n, int from){
  int indexOfSmallest = from;
  int i;

  for(i = from; i < n; i++)
	if(a[i] < a[indexOfSmallest])
  	indexOfSmallest = i;
  return indexOfSmallest;
}

void Sort(int* x){
//	int* x = (int *)a;
  int i, indexOfSmallest, t;
  for(i = 0; i < n ; i++){
	indexOfSmallest = findIndexOfSmallest(x, n, i);
	t = x[i];
	x[i] = x[indexOfSmallest];
	x[indexOfSmallest] = t;
  }
  for(i = 0; i < n; i++)
	printf("%d, ", x[i]);
  printf("\n------------------------------\n");
  //return NULL;
}

void Merge(int a[], int b[], int c[]){
  int aIndex = 0, bIndex = 0, cIndex = 0, i;
  while(aIndex < n && bIndex < n)
	if(a[aIndex] < b[bIndex])
  	c[cIndex++] = a[aIndex++];
	else
  	c[cIndex++] = b[bIndex++];

  while(aIndex < n)
	c[cIndex++] = a[aIndex++];

  while(bIndex < n)
	c[cIndex++] = b[bIndex++];
  for(i = 0; i < 2 * n; i++)
	printf("%d, ", c[i]);
  printf("\n^^^^^^^^^^^^^^^^^^^^^^^^^^\n");

}
