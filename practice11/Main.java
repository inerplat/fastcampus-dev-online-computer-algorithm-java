package practice11;

import java.util.*;

public class Main {

    static int[] chk;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        chk = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (chk[i] == 0) {
                chk[i] = 1;
                bfs(graph, i);
                cnt++;
            }
        }
        System.out.println(cnt - 1);
    }

    static void bfs(List<Integer>[] graph, int x) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        while (!q.isEmpty()) {
            int src = q.poll();
            for (int des : graph[src]) {
                if (chk[des] == 0) {
                    chk[des] = 1;
                    q.offer(des);
                }
            }
        }
    }
}
