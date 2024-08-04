package practice27;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();
        long x3 = sc.nextLong();
        long y3 = sc.nextLong();
        String require = sc.next();

        long crossProduct = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2+ x1*y3);
        if(crossProduct > 0) {
            if(require.equals("FAST")) System.out.println(1);
            else if(require.equals("SLOW")) System.out.println(-1);
        }
        else if(crossProduct < 0) {
            if(require.equals("FAST")) System.out.println(-1);
            else if(require.equals("SLOW")) System.out.println(1);
        }
    }
}
