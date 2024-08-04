package practice02;


import java.util.Scanner;

public class Main {
    static long[] cache = new long[1001];

    static long hanoi(int n) {
        if (cache[n] != 0) return cache[n];
        // base case
        if (n == 0) return 0;
        if (n == 1) return 1;

        // recursive case
        cache[n] = 2 * hanoi(n - 1) + 1;
        return cache[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        long answer = hanoi(n) - hanoi(k) + hanoi(k - 1);
        System.out.println(answer);
    }
}
