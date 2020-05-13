#include <stdio.h>
#include <string.h>

int main()
{
	char a;									//1
	int m;									//2
	float x;									//3
	char z[30];

	a = 'A';								      //4
	printf("The value of the variable a is: %c\n", a);		//5
	printf("The value of the variable a is: %d\n", a);		//6
	a = 65;								      //7
	printf("The value of the variable a is: %c\n", a);		//8
	m = -123;								      //9
	printf("The value of the variable m is: %d\n", m);		//10
	x = -25.7;								      //11
	printf("The value of the variable x is: %f\n", x);		//12
	strcpy(z, "Hello World");						//13
	printf("The value of the variable a is: %s\n", z);		//14
	printf("Finally we display the value of all of a to be %c, m + 5 to be %d, x to be %f, and z to be %s: ", a, m + 5, x, z);			//15
	return 0;
}
