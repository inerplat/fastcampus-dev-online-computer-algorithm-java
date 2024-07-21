package practice12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    static void init(int[] parent, int n) {
        for (int i = 1; i <= n; i++)
            parent[i] = i;
    }
    static int find(int[] parent, int x){
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
        List<Graph2> g = new ArrayList<Graph2>();

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            g.add(new Graph2(u, v, c));
        }
        int ans = 0;
        int[] par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
        g.sort(null);
        for (Graph2 x : g) {
            if (find(par, x.src) != find(par, x.dest)) {
                ans += x.cost;
                union(par, x.src, x.dest);
            }
        }
        System.out.println(ans);
    }
}


class Graph2 implements Comparable<Graph2> {
    public int src;
    public int dest;
    public int cost;
    public Graph2(int s, int d, int c) {
        this.src = s;
        this.dest = d;
        this.cost = c;
    }
    @Override
    public int compareTo(Graph2 o) {
        return this.cost - o.cost;
    }
}