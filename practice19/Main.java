package practice19;

import java.util.Scanner;

public class Main {

    public static int search(int[] arr, int start, int end, int key) {
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (arr[mid] >= key) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static int lis(int[] arr, int n) {
        int[] lis = new int[n + 1];
        lis[1] = arr[1];
        int idx = 1;
        for (int i = 2; i <= n; i++) {
            if (lis[idx] < arr[i]) {
                lis[++idx] = arr[i];
            } else {
                int pos = search(lis, 1, idx, arr[i]);
                lis[pos] = arr[i];
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        //        int[] lis = new int[n + 1];
        int[] d = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        d[1] = arr[1];
        int idx = 1;
        for (int i = 2; i <= n; i++) {
            if (d[idx] < arr[i]) {
                d[++idx] = arr[i];
            } else {
                int pos = search(d, 1, idx, arr[i]);
                d[pos] = arr[i];
            }
        }
        System.out.println(idx);
    }
}
