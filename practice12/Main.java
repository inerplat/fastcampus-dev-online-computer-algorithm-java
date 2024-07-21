package practice12;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Graph>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            g[u].add(new Graph(v, c));
            g[v].add(new Graph(u, c));
        }
        boolean[] chk = new boolean[n + 1];
        int ans = 0;
        PriorityQueue<Graph> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        pq.offer(new Graph(1, 0));
        while (!pq.isEmpty()) {
            Graph cur = pq.poll();
            int dest = cur.dest;
            int cost = cur.cost;
            if (!chk[dest]) {
                chk[dest] = true;
                ans += cost;
                for (Graph nxt : g[dest]) {
                    pq.offer(nxt);
                }
            }
        }
        for(int i = 1; i <= n; i++){
            if(!chk[i]){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }
}


class Graph implements Comparable<Graph> {
    public int dest;
    public int cost;
    public Graph(int d, int c) {
        dest = d;
        cost = c;
    }

    @Override
    public int compareTo(Graph o) {
        return this.cost - o.cost;
    }
}