package practice02;

import java.util.Scanner;

public class Main {
    static long[] cache = new long[1001];

    static long hanoi(int n) {
        // memoization
        if (cache[n] != 0) return cache[n];
        if (n == 0) return 0;
        // base case
        if (n == 1) return 1;
        // recursive case
        cache[n] = 2 * hanoi(n - 1) + 1;
        return cache[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(hanoi(n) - hanoi(k) + hanoi(k - 1));
    }
}
