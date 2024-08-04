package final01;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();

        List<Graph>[] graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= e; i++) {
            int s = sc.nextInt(), d = sc.nextInt(), c = sc.nextInt();
            graph[s].add(new Graph(d, c));
            graph[d].add(new Graph(s, c));
        }

        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2 - 1);
        boolean[] visited = new boolean[v + 1];
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.offer(new Graph(start, 0));

        dist[start] = 0;
        while (!pq.isEmpty()) {
            Graph cur = pq.poll();
            int current = cur.dest;
            if (visited[current]) continue;
            visited[current] = true;
            for (Graph g : graph[current]) {
                int next = g.dest;
                if (dist[next] > dist[current] + g.time) {
                    dist[next] = dist[current] + g.time;
                    pq.offer(new Graph(next, dist[next]));
                }
            }
        }
        if (!visited[end]) {
            System.out.println(-1);
            return;
        }
        System.out.println(dist[end]);
    }
}

class Graph implements Comparable<Graph> {
    int dest;
    int time;

    Graph(int d, int t) {
        dest = d;
        time = t;
    }

    @Override
    public int compareTo(Graph o) {
        return this.time - o.time;
    }
}