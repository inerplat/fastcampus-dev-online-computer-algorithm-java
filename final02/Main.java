package final02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int addCount = scanner.nextInt();
        int mulCount = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        scanner.close();

        System.out.println(calculateMaxValue(numbers, addCount, mulCount));
    }

    private static int calculateMaxValue(int[] numbers, int addCount, int mulCount) {
        int n = numbers.length;
        int[][][] dp = new int[n][addCount + 1][mulCount + 1];

        for (int a = 0; a <= addCount; a++) {
            for (int m = 0; m <= mulCount; m++) {
                dp[0][a][m] = numbers[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int a = 0; a <= addCount; a++) {
                for (int m = 0; m <= mulCount; m++) {
                    dp[i][a][m] = Integer.MIN_VALUE;
                    if (a > 0) {
                        dp[i][a][m] = Math.max(dp[i][a][m], dp[i - 1][a - 1][m] + numbers[i]);
                    }
                    if (m > 0) {
                        dp[i][a][m] = Math.max(dp[i][a][m], dp[i - 1][a][m - 1] * numbers[i]);
                    }
                }
            }
        }

        return dp[n - 1][addCount][mulCount];
    }
}
