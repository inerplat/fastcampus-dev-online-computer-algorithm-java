package practice21;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] dist = new int[301][301];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 1; i <= m; i++) {
            int src = sc.nextInt(), dest = sc.nextInt(), cost = sc.nextInt();
            if (dist[src][dest] > cost) {
                dist[src][dest] = cost;
                dist[dest][src] = cost;
            }
        }
        for (int i = 1; i <= n; i++) dist[i][i] = 0;

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE / 2) System.out.print("INF ");
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
