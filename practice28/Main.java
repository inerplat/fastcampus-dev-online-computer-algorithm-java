package practice28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Point baseP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] points = new Point[n];
        int base = 0;
        for (int i = 0; i < n; i++) {
            points[i] = new Point(sc.nextInt(), sc.nextInt());
            if (points[i].y < points[base].y || (points[i].y == points[base].y && points[i].x < points[base].x)) {
                base = i;
            }
        }
        Point temp = points[0];
        points[0] = points[base];
        points[base] = temp;
        baseP = points[0];
        Arrays.sort(points, 1, n, (a, b) -> compare(a, b));
        List<Point> lower = new ArrayList<>();
        for (Point p : points) {
            while (lower.size() >= 2 && ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }
        System.out.println(n - lower.size());
    }

    static int compare(Point a, Point b) {
        int ccwValue = ccw(baseP, a, b);
        if (ccwValue == 0) {
            int distanceA = Math.abs(baseP.x - a.x) + Math.abs(baseP.y - a.y);
            int distanceB = Math.abs(baseP.x - b.x) + Math.abs(baseP.y - b.y);
            return distanceA - distanceB;
        }
        return -ccwValue;
    }

    static int ccw(Point o, Point a, Point b) {
        return (o.x * a.y + a.x * b.y + b.x * o.y) - (o.y * a.x + a.y * b.x + b.y * o.x);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}