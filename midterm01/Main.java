package midterm01;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] sales = new int[n];
        for (int i = 0; i < n; i++) {
            sales[i] = sc.nextInt();
        }
        sc.close();

        boolean canAggro = divide(sales, n, x);
        if (canAggro) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean divide(int[] sales, int n, int x) {
        int left = 1;
        int right = n;
        int minLength = -1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (decision(sales, n, mid, x)) {
                minLength = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return minLength != -1;
    }

    public static boolean decision(int[] sales, int n, int length, int x) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += sales[i];
        }
        if (sum / length >= x) return true;
        for (int i = length; i < n; i++) {
            sum += sales[i] - sales[i - length];
            if (sum / length >= x) return true;
        }
        return false;
    }
}
