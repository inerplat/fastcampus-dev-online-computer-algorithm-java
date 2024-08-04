package practice01;

import java.util.Scanner;

public class Main {

    static int fibo(int n) {
        // base case
        if (n == 1) return 1;
        else if (n == 0) return 0;

        // recursive case
        if (n > 1) return fibo(n - 1) + fibo(n - 2);
        else return fibo(n + 2) - fibo(n + 1);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(fibo(n));
    }
}
