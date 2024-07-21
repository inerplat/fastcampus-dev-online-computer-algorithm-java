package etc.sort.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void divide(List<Student> arr, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        divide(arr, start, mid);
        divide(arr, mid + 1, end);
        merge(arr, start, end);
    }

    static void merge(List<Student> arr, int start, int end) {
        int mid = (start + end) / 2;
        int left = start;
        int right = mid + 1;
        List<Student> temp = new ArrayList<>();

        while (left <= mid && right <= end) {
            if (arr.get(left).compareTo(arr.get(right)) > 0) {
                temp.add(arr.get(left++));
            } else {
                temp.add(arr.get(right++));
            }
        }
        while (left <= mid) {
            temp.add(arr.get(left++));
        }
        while (right <= end) {
            temp.add(arr.get(right++));
        }

        for (int i = 0; i < temp.size(); i++) {
            arr.set(i + start, temp.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(sc.nextInt(), sc.nextInt()));
        }
        divide(students, 0, n - 1);

    }
}

class Student implements Comparable<Student> {
    int age;
    int height;

    public Student(int age, int height) {
        this.age = age;
        this.height = height;
    }

    @Override
    public int compareTo(Student o) {
        if (this.age == o.age)
            return this.height - o.height;
        return this.age - o.age;
    }
}