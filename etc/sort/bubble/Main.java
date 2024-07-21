package etc.sort.bubble;

public class Main {

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // swap
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 7, 3, 1};
        System.out.println("Array before sorting:");
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
        bubbleSort(arr);
        System.out.println("Array after sorting:");
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}