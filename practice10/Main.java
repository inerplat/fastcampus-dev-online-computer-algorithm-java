package practice10;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(), e = sc.nextInt();
        List<Graph>[] adjList = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            adjList[src].add(new Graph(dest, cost));
            adjList[dest].add(new Graph(src, cost));
        }
        int q = sc.nextInt();
        for (int i = 1; i <= q; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();

            if (src == dest) {
                System.out.println(0);
                continue;
            }
            boolean found = false;
            int minCost = Integer.MAX_VALUE;
            for (Graph g : adjList[src]) {
                if (g.dest == dest) {
                    found = true;
                    if (g.cost < minCost) {
                        minCost = g.cost;
                    }
                }
            }
            if (found) {
                System.out.println(minCost);
            } else {
                System.out.println(-1);
            }
        }

    }
}

class Graph {
    int dest, cost;

    public Graph(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}