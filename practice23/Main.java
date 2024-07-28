package practice23;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();
        System.out.println(rabinKarp(pattern, text));
    }

    private static final int D = 256;
    private static final int M = Integer.MAX_VALUE;

    private static int rabinKarp(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        long patternHash = hash(pattern, m);
        long textHash = hash(text, m);
        int count = 0;

        if (patternHash == textHash && checkEquality(text, pattern, 0)) {
            count++;
        }

        long basePower = 1;
        for (int i = 1; i <= m - 1; i++) {
            basePower = (basePower * D) % M;
        }

        for (int i = 1; i <= n - m; i++) {
            textHash = (textHash - text.charAt(i - 1) * basePower % M + M) % M;
            textHash = textHash * D % M;
            textHash = (textHash + text.charAt(i + m - 1)) % M;
            if (patternHash == textHash && checkEquality(text, pattern, i)) {
                count++;
            }
        }

        return count;
    }

    private static long hash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash * D + str.charAt(i)) % M;
        }
        return hash;
    }

    private static boolean checkEquality(String text, String pattern, int start) {
        for (int i = 0; i < pattern.length(); i++) {
            if (text.charAt(start + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}