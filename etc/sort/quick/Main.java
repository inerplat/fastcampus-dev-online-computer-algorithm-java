package etc.sort.quick;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void quick(List<Student> arr, int start, int end) {
        if (start >= end) return;
        Student pivot = arr.get(start);
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (left <= end && arr.get(left).compareTo(pivot) <= 0) left++;
            while (right > start && arr.get(right).compareTo(pivot) > 0) right--;
            if (left < right) {
                Student tmp = arr.get(left);
                arr.set(left, arr.get(right));
                arr.set(right, tmp);
            }
        }
        Student tmp = arr.get(start);
        arr.set(start, arr.get(right));
        arr.set(right, tmp);

        quick(arr, start, right - 1);
        quick(arr, right + 1, end);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(sc.nextInt(), sc.nextInt()));
        }

        quick(students, 0, n - 1);
        for (Student student : students) {
            System.out.println(student.age + " " + student.height);
        }
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