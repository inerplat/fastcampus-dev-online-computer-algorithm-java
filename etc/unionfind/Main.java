package etc.unionfind;

public class Main {
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
}
