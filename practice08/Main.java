package practice08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mars = new int[n + 1][n + 1];
        int[][] check = new int[n + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                mars[i][j] = sc.nextInt();
            }
        }
        Stack<Point> stack = new Stack<>();
        Queue<Point> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (mars[i][j] == 1 && check[i][j] == 0) {
                    stack.push(new Point(i, j));
                    q.add(new Point(i, j));
                    // int size = dfs(mars, check, stack);
                    int size = bfs(mars, check, q);
                    max = Math.max(max, size);
                }
            }
        }
        System.out.println(max);

    }

    public static boolean isSafe(int[][] check, int r, int c) {
        return r >= 1 && r <= check.length - 1 && c >= 1 && c <= check.length - 1 && check[r][c] == 0;
    }

    public static int dfs(int[][] mars, int[][] check, Stack<Point> stack) {
        int size = 0;
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int r = p.row;
            int c = p.col;
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            if (isSafe(check, r, c) && mars[r][c] == 1) {
                check[r][c] = 1;
                size++;
                for (int i = 0; i < 4; i++) {
                    int nx = r + dx[i];
                    int ny = c + dy[i];
                    stack.push(new Point(nx, ny));
                }
            }
        }
        return size;
    }

    public static int dfs2(int[][] mars, int[][] check, int r, int c) {
        if (!isSafe(check, r, c) || mars[r][c] == 0) return 0;
        check[r][c] = 1;
        return 1 + dfs2(mars, check, r - 1, c) + dfs2(mars, check, r + 1, c) + dfs2(mars, check, r, c - 1) + dfs2(mars, check, r, c + 1);
    }

    public static int bfs(int[][] mars, int[][] check, Queue<Point> q){
        int size = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(!q.isEmpty()) {
            Point p = q.poll();
            int r = p.row;
            int c = p.col;
            if (isSafe(check, r, c) && mars[r][c] == 1) {
                check[r][c] = 1;
                size++;
                for (int i = 0; i < 4; i++) {
                    int nx = r + dx[i];
                    int ny = c + dy[i];
                    q.add(new Point(nx, ny));
                }
            }
        }
        return size;
    }
}

class Point {
    public int row;
    public int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}