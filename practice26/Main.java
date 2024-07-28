package practice26;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y, i + 1));
        }

        points.sort(Comparator.comparingInt(p -> p.x));

        Pair closest = divide(points, 0, n - 1);
        if (closest.first.index < closest.second.index)
            System.out.println(closest.first.index + " " + closest.second.index);
        else System.out.println(closest.second.index + " " + closest.first.index);
    }

    public static int dist(Point p, Point q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }

    static Pair bruteForce(List<Point> points, int start, int end) {
        int minDist = Integer.MAX_VALUE;
        Pair closestPair = new Pair(points.get(start), points.get(start));
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                int d = dist(points.get(i), points.get(j));
                if (d < minDist) {
                    minDist = d;
                    closestPair = new Pair(points.get(i), points.get(j));
                }
            }
        }
        return closestPair;
    }

    public static Pair divide(List<Point> points, int start, int end) {
        if (end - start <= 3) {
            return bruteForce(points, start, end);
        }

        int mid = (start + end) / 2;
        Pair leftArea = divide(points, start, mid);
        Pair rightArea = divide(points, mid + 1, end);

        int leftDist = dist(leftArea.first, leftArea.second);
        int rightDist = dist(rightArea.first, rightArea.second);

        int d = Math.min(leftDist, rightDist);
        Pair minPair = leftDist < rightDist ? leftArea : rightArea;

        List<Point> band = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            int diff = points.get(mid).x - points.get(i).x;
            if (diff * diff < d) {
                band.add(points.get(i));
            }
        }
        band.sort(Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < band.size(); i++) {
            for (int j = i + 1; j < band.size() && j <= i + 4; j++) {
                int newDist = dist(band.get(i), band.get(j));
                if (newDist < d) {
                    d = newDist;
                    minPair = new Pair(band.get(i), band.get(j));
                }
            }
        }

        return minPair;
    }
}

class Point {
    int x, y, index;

    Point(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
}

class Pair {
    Point first, second;

    Pair(Point first, Point second) {
        this.first = first;
        this.second = second;
    }
}