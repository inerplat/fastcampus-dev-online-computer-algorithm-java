package practice07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] maze = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }
        String answer = bfs(maze, n);
        System.out.println(answer);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static String bfs(int[][] maze, int n) {
        if (maze[1][1] == 1 || maze[n][n] == 1) return "SAD";
        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 1));
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            Point pos = queue.remove();
            if (pos.row == n && pos.col == n) {
                return "HAPPY";
            }
            for(int i = 0; i < 4; i++) {
                int nr = pos.row + dr[i];
                int nc = pos.col + dc[i];
                if (isSafe(nr, nc, n) && maze[nr][nc] == 0 && !visited[nr][nc]) {
                    queue.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        return "SAD";
    }



    static boolean isSafe(int r, int c, int n) {
        return r > 0 && r <= n && c > 0 && c <= n;
    }
}
