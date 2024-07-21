package practice13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
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
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            graph[a].add(new Graph(b, c));
        }

        int s = sc.nextInt(), d = sc.nextInt();
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        boolean[] visited = new boolean[v + 1];
        int[] last = new int[v + 1];
        PriorityQueue<Graph> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.offer(new Graph(s, 0));
        dist[s] = 0;
        last[s] = s;

        while (!pq.isEmpty()) {
            Graph cur = pq.poll();
            int current = cur.dest;
            if (visited[current]) continue;
            visited[current] = true;
            for (Graph g : graph[current]) {
                int next = g.dest;
                if (dist[next] > dist[current] + g.time) {
                    dist[next] = dist[current] + g.time;
                    last[next] = current;
                    pq.offer(new Graph(next, dist[next]));
                }
            }
        }
        if (!visited[d]) {
            System.out.println(-1);
            return;
        }
        int cur = d;
        List<Integer> path = new ArrayList<>();
        while (cur != last[cur]) {
            path.add(cur);
            cur = last[cur];
        }
        path.add(s);
        System.out.println(dist[d]);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }
}

class Graph {
    int dest;
    int time;

    Graph(int d, int t) {
        dest = d;
        time = t;
    }
}