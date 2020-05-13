#include <stdio.h>

int main()
{
	char x;
	
	printf("Enter a character: ");
	scanf("%c", &x);
	
	switch(x)
	{
	  case 'A': printf("You entered A.\n"); //break;
	  case 'B': printf("You entered B.\n"); break;
	  default: printf("You entered a character other than A or B.\n");
	}
	return 0;
}
