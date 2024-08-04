package practice22;

import java.util.Scanner;

public class Main {
    static int min3(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s1 = (" " + sc.next()).toCharArray();
        char[] s2 = (" " + sc.next()).toCharArray();
        int n = s1.length - 1;
        int m = s2.length - 1;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(s1[i] == s2[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min3(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;

                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
