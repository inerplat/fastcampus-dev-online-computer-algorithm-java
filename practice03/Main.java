package practice03;

import java.util.Scanner;

public class Main {

    static int euclid(int a, int b) {
        // base case
        if (b == 0) return a;
        // recursive case
        return euclid(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        System.out.println(euclid(a, b));
    }
}
