package practice05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int mx = Integer.MIN_VALUE;

        String line = br.readLine();
        String[] nums = line.split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(nums[i - 1]);
            mx = Math.max(mx, arr[i]);
        }

        int exp = 1;
        List<Integer>[] bucket = new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            bucket[i] = new ArrayList<>();
        }
        while (mx >= exp) {
            for (int i = 1; i <= n; i++) {
                int digit = (arr[i] / exp) % 10;
                bucket[digit].add(arr[i]);
            }
            int index = 1;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < bucket[i].size(); j++) {
                    arr[index++] = bucket[i].get(j);
                }
                bucket[i].clear();
            }
            exp *= 10;
        }
        BufferedWriter  bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] + " ");
        }
        bw.flush();
    }
}
