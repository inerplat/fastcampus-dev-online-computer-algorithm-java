package practice18;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        Fish[] fish = new Fish[n + 1];
        for (int i = 1; i <= n; i++) {
            int weight = sc.nextInt();
            int cost = sc.nextInt();
            fish[i] = new Fish(cost, weight);
        }

//        int[][] dp = new int[n + 1][m + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int w = 1; w <= m; w++) {
//                if (w - fish[i].weight < 0) {
//                    dp[i][w] = dp[i - 1][w];
//                } else {
//                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - fish[i].weight] + fish[i].cost);
//                }
//            }
//        }
//        System.out.println(dp[n][m]);
        int[] prev = new int[m + 1];
        int[] dp2 = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= m; w++) {
                if (w - fish[i].weight < 0) {
                    dp2[w] = prev[w];
                } else {
                    dp2[w] = Math.max(prev[w], prev[w - fish[i].weight] + fish[i].cost);
                }
            }
            for (int j = 1; j <= m; j++) {
                prev[j] = dp2[j];
                dp2[j] = 0;
            }
        }
        System.out.println(prev[m]);
    }
}

class Fish {
    int cost;
    int weight;

    Fish(int c, int w) {
        this.cost = c;
        this.weight = w;
    }
}
