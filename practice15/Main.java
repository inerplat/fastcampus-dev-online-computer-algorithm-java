package practice15;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(), e = sc.nextInt();
        List<Integer>[] graph = new ArrayList[v + 1];
        int[] inDegree = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= e; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            graph[a].add(b);
            inDegree[b]++;
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= v; i++) {
            if (inDegree[i] == 0) {
                result.add(i);
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    result.add(next);
                    q.offer(next);
                }
            }
        }
        if (result.size() != v) {
            System.out.println("ERROR");
            System.exit(0);
        }

        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}
