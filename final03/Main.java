package final03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int D = 257;
    static long M = Long.MAX_VALUE / D;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();

        System.out.println(divide(text));
    }

    private static int divide(String s) {
        int left = 1, right = s.length();
        while (left <= right) {
            int mid = (left + right) / 2;
            if (search(s, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static boolean search(String s, int length) {
        List<SubstringHash> hashes = new ArrayList<>();
        long currentHash = hash(s, length);
        long baseL = 1;

        for (int i = 0; i < length - 1; i++) {
            baseL = (baseL * D) % M;
        }

        hashes.add(new SubstringHash(currentHash, s.substring(0, length)));

        for (int i = length; i < s.length(); i++) {
            currentHash = (currentHash - s.charAt(i - length) * baseL) % M;
            if (currentHash < 0) currentHash += M;
            currentHash = (currentHash * D + s.charAt(i)) % M;
            hashes.add(new SubstringHash(currentHash, s.substring(i - length + 1, i + 1)));
        }

        hashes.sort(null);
        for (int i = 1; i < hashes.size(); i++) {
            if (hashes.get(i).hash == hashes.get(i - 1).hash && hashes.get(i).str.equals(hashes.get((i - 1)).str)) {
                return true;
            }
        }
        return false;
    }

    static long hash(String str, int length) {
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (h * D + str.charAt(i)) % M;
        }
        return h;
    }
}


class SubstringHash implements Comparable<SubstringHash> {
    long hash;
    String str;

    SubstringHash(long hash, String str) {
        this.hash = hash;
        this.str = str;
    }


    @Override
    public int compareTo(SubstringHash o) {
        return Long.compare(this.hash, o.hash);
    }
}
