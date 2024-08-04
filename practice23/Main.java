package practice23;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();
        System.out.println(rabinKarp(pattern, text));
    }

    static int rabinKarp(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        long patternHash = hash(pattern, m);
        long textHash = hash(text, m);
        int count = 0;

        if (patternHash == textHash && checkEquality(text, pattern, 0)) {
            count++;
        }
        long basePower = 1;
        for (int i = 1; i < m; i++) {
            basePower = (basePower * D) % M;
        }
        for (int i = 1; i <= n - m; i++) {
            textHash = ((textHash - text.charAt(i - 1) * basePower % M) + M) % M;
            textHash = textHash * D % M;
            textHash = (textHash + text.charAt(i + m - 1)) % M;
            if (patternHash == textHash && checkEquality(text, pattern, i)) {
                count++;
            }
        }
        return count;
    }

    static boolean checkEquality(String text, String pattern, int start) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != text.charAt(start + i)) {
                return false;
            }
        }
        return true;
    }

    static int D = 257;
    static Long M = Long.MAX_VALUE / D;

    static long hash(String str, int length) {
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (h * D + str.charAt(i)) % M;
        }
        return h;
    }

}