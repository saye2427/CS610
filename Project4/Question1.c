#include <stdio.h>

int pow1(int m, int n){
    int i, ans = 1;
    for(i = 1; i <= n; i++)
        ans = ans * m;
    return ans;
}

int max(int m, int n){
    if(m > n)
        return m;
    return n;
}

int min(int m, int n){
    if(m < n)
        return m;
    return n;
}

void func (int m, int n, int func(int, int)){
    printf("%d\n",func(m,n));
}

int main (){
    // This function is complete. Do not change it.
    int x, y;
    printf("Enter two positive integers: ");
    scanf("%d%d", &x, &y);
    func(x, y, pow1);
    func(x, y, max);
    func(x, y, min);
    return 0;
}
