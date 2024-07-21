package etc.sort.select;

public class Main {
    void selectSort(int[] arr) {
        int end = arr.length;
        for(int start = 0; start < end; start++) {
            int min = start;
            for(int j = start + 1; j < end; j++) {
                if(arr[j] < arr[min]) min = j;
            }
            int tmp = arr[start];
            arr[start] = arr[min];
            arr[min] = tmp;
        }
    }

    public static void main(String[] args) {

    }
}
