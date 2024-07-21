package practice06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] daily = new int[n];
        for (int i = 0; i < n; i++) {
            daily[i] = sc.nextInt();
        }

        System.out.println(search(daily, n, m));
    }

    private static int search(int[] daily, int n, int m) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (decision(daily, n, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean decision(int[] dailyIncome, int n, int m, int days) {
        int sum = 0;
        for (int i = 0; i < days; i++) {
            sum += dailyIncome[i];
        }
        if (sum >= m) {
            return true;
        }
        for (int i = days; i < n; i++) {
            sum += dailyIncome[i] - dailyIncome[i - days];
            if (sum >= m) {
                return true;
            }
        }
        return false;
    }
}
