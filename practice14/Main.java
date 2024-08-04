package practice14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(), e = sc.nextInt();
        List<Graph>[] graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= e; i++) {
            int s = sc.nextInt(), d = sc.nextInt(), c = sc.nextInt();
            graph[s].add(new Graph(d, c));
        }
        int z = sc.nextInt();
        int x = sc.nextInt();

        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        int[] last = new int[v + 1];
        dist[z] = 0;
        last[z] = z;
        for (int i = 1; i < v; i++) {
            for (int j = 1; j <= v; j++) {
                for (Graph next : graph[j]) {
                    if (dist[next.dest] > dist[j] + next.cost) {
                        dist[next.dest] = dist[j] + next.cost;
                        last[next.dest] = j;
                    }
                }
            }
        }
        boolean cycle = false;
        for (int j = 1; j <= v; j++) {
            for (Graph next : graph[j]) {
                if (dist[next.dest] > dist[j] + next.cost) {
                    cycle = true;
                    System.out.println("GAZUA");
                    return;
                }
            }
        }

        if (dist[x] == Integer.MAX_VALUE / 2) {
            System.out.println("RAGE");
            return;
        }
        System.out.println(dist[x]);
        int curr = x;
        List<Integer> path = new ArrayList<>();
        while (curr != last[curr]) {
            path.add(curr);
            curr = last[curr];
        }
        path.add(z);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }
}

class Graph {
    int dest;
    int cost;

    Graph(int d, int c) {
        dest = d;
        cost = c;
    }
}