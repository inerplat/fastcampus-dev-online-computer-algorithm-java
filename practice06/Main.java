package practice06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] daily = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            daily[i] = sc.nextInt();
        }
        System.out.println(search(daily, n, m, 1, n));
    }

    private static int search(int[] daily, int n, int m, int start, int end) {
        // base case
        if (start >= end) return start;
        int mid = (start + end) / 2;
        if (decision(daily, n, m, mid)) {
            return search(daily, n, m, start, mid);
        } else {
            return search(daily, n, m, mid + 1, end);
        }
    }

    private static boolean decision(int[] daily, int n, int m, int days) {
        int sum = 0;
        for (int i = 1; i <= days; i++) {
            sum += daily[i];
        }
        if (sum >= m) return true;
        for (int i = days; i <= n; i++) {
            sum += daily[i] - daily[i - days];
            if (sum >= m) return true;
        }
        return false;
    }
}
