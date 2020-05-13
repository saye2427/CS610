#include <stdio.h>
#include <stdarg.h>

int total(int arg, ...){
    // Complete the body of this function. Do not change:
    // int total(int arg, ...)
    va_list valst;
    
    int sum=0;
    va_start(valst, arg);
    
    for(int i=arg; i!=0; i=va_arg(valst, int))
        sum+=i;
    
    va_end(valst);
    return sum;
}

void main(){
    // This function is complete. Do not change it.
    int a, b, c, d, e;
    printf("Enter 5 non-zero integers: \n");
    scanf("%d%d%d%d%d", &a, &b, &c, &d, &e);
    printf("%d\n", total(a, b, c, d, e, 0));
    printf("%d\n", total(a, b, c, d, 0));
    printf("%d\n", total(a, b, c, 0));
    printf("%d\n", total(a, b, 0));
}
