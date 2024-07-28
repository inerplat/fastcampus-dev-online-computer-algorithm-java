package practice24;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new java.util.Scanner(System.in);

        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        int count = search(pattern, text);
        System.out.println(count);
    }

    public static int search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int[] f = failure(pattern);

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
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

    public static int[] failure(String pattern) {
        int m = pattern.length();
        int[] f = new int[m];
        int length = 0;
        int i = 1;
        f[0] = 0;

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
