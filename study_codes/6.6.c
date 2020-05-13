#include <stdio.h>
#include <stdlib.h>

int main()
{
	int month, days;
	
	printf("Enter a number 1 to 12 for a month: ");
	scanf("%d", &month);
	
	switch(month)
	{
		case 2: days = 28; break;
		case 8: days = 29; break;
		case 4: case 5: case 6: case 9: case 11: days = 30; break;
		case 1: case 3: case 7: case 10: case 12: days = 31; break;
		default: printf("Wrong month number %d\n", month); //exit(0);
	}
	printf("There are %d days in month %d\n", days, month);
	return 0;
}
