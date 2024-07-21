package practice16;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int price = sc.nextInt();
        int n = sc.nextInt();
        int[] coin = new int[n + 1];
        int[] dp = new int[10000 * 2 + 10];

        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

//        for (int i = 1; i <= n; i++) {
//            coin[i] = sc.nextInt();
//            for (int j = 0; j <= price; j++) {
//                dp[j + coin[i]] = Math.min(dp[j + coin[i]], dp[j] + 1);
//            }
//        }
        for (int i = 1; i <= n; i++) {
            coin[i] = sc.nextInt();
            for (int j = coin[i]; j <= price; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }
        if(dp[price] == Integer.MAX_VALUE / 2)
            System.out.println(-1);
        else
            System.out.println(dp[price]);
    }
}
