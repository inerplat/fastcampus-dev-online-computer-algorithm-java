package practice08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mars = new int[n + 1][n + 1];
        int[][] check = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                mars[i][j] = sc.nextInt();
            }
        }
        Queue<Point> q = new LinkedList<>();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (mars[i][j] == 1 && check[i][j] == 0) {
                    q.add(new Point(i, j));
                    int size = bfs(mars, check, q);
                    max = Math.max(max, size);
                }
            }
        }
        System.out.println(max);
    }

    static int bfs(int[][] mars, int[][] check, Queue<Point> q) {
        int size = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.row;
            int c = p.col;
            if(isSafe(check, r, c) && mars[r][c] == 1) {
                check[r][c] = 1;
                size++;
                for(int i = 0 ; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    q.add(new Point(nr, nc));
                }
            }
        }
        return size;
    }

    static boolean isSafe(int[][] check, int r, int c) {
        return r >= 1 && r <= check.length - 1 && c >= 1 && c <= check.length - 1 && check[r][c] == 0;
    }
}

class Point {
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}