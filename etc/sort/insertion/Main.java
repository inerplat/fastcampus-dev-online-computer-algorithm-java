package etc.sort.insertion;

public class Main {
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int target = arr[i];
            int j = i - 1;
            while (j >= 0 && target < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = target;
        }
    }

    public static void main(String[] args){

    }
}
