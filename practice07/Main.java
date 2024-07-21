package practice07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] maze = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }
        String answer = dfs(maze, n);
        System.out.println(answer);
    }

    private static String bfs(int[][] maze, int n) {
        if (maze[1][1] == 1 || maze[n][n] == 1) {
            return "SAD";
        }

        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(1, 1));
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            if (pos.x == n && pos.y == n) {
                return "HAPPY";
            }
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (isSafe(nx, ny, n) && maze[nx][ny] == 0 && !visited[nx][ny]) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return "SAD";
    }

    private static String dfs(int[][] maze, int n) {
        if (maze[1][1] == 1 || maze[n][n] == 1) {
            return "SAD";
        }

        boolean[][] visited = new boolean[n + 1][n + 1];
        Stack<Position> stack = new Stack<>();
        stack.push(new Position(1, 1));
        visited[1][1] = true;

        while (!stack.isEmpty()) {
            Position pos = stack.pop();
            if (pos.x == n && pos.y == n) {
                return "HAPPY";
            }
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (isSafe(nx, ny, n) && maze[nx][ny] == 0 && !visited[nx][ny]) {
                    stack.push(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return "SAD";
    }

    private static boolean isSafe(int x, int y, int n) {
        return x > 0 && x <= n && y > 0 && y <= n;
    }
}
