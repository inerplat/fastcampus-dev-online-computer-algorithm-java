package practice24;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();
        System.out.println(search(pattern, text));
    }

    static int search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int[] f = failure(pattern);

        int i = 0; // text
        int j = 0; // pattern
        int count = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                count++;
                j = f[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = f[j - 1];
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    static int[] failure(String pattern) {
        int m = pattern.length();
        int[] f = new int[m];
        int length = 0;
        f[0] = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                f[i] = ++length;
                i++;
            } else {
                if (length != 0) {
                    length = f[length - 1];
                } else {
                    f[i] = 0;
                    i++;
                }
            }
        }
        return f;
    }
}
