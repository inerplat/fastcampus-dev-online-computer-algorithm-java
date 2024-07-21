package practice09;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(), e = sc.nextInt();
        int[][] arr = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++)
            Arrays.fill(arr[i], Integer.MAX_VALUE);

        for (int i = 1; i <= e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            if (arr[src][dest] > cost) arr[src][dest] = cost;
            if (arr[dest][src] > cost) arr[dest][src] = cost;
        }
        for (int i = 1; i <= v; i++) {
            arr[i][i] = 0;
        }

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            if (arr[src][dest] == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(arr[src][dest]);
        }
    }

}
