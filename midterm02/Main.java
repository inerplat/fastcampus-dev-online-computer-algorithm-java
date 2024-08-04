package midterm02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void init(int[] parent, int n) {
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static void union(int[] parent, int a, int b) {
        int x = find(parent, a);
        int y = find(parent, b);
        parent[y] = x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> g = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            g.add(new Edge(u, v, c));
        }
        g.sort(null);
        int[] par = new int[n + 1];
        int ans = 0;
        init(par, n);
        for (Edge x : g) {
            if (find(par, x.src) != find(par, x.dest)) {
                ans += x.cost;
                union(par, x.src, x.dest);
            }
        }
        int root = find(par, 1);
        for (int i = 2; i <= n; i++) {
            if (find(par, i) != root) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }
}

class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int cost;

    public Edge(int s, int d, int c) {
        this.src = s;
        this.dest = d;
        this.cost = c;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}