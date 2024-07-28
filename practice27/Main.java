package practice27;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long x1 = scanner.nextLong();
        long y1 = scanner.nextLong();
        long x2 = scanner.nextLong();
        long y2 = scanner.nextLong();
        long x3 = scanner.nextLong();
        long y3 = scanner.nextLong();
        String requirement = scanner.next();

        long crossProduct = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);


        if (crossProduct > 0) {
            if (requirement.equals("FAST")) System.out.println(1);
            else if (requirement.equals("SLOW")) System.out.println(-1);
        }
        else if (crossProduct < 0) {
            if (requirement.equals("FAST")) System.out.println(-1);
            else if (requirement.equals("SLOW")) System.out.println(1);
        }
    }
}
