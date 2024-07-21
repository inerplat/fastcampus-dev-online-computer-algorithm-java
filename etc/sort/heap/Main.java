package etc.sort.heap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] data = new Student[n + 1];
        for (int i = 1; i <= n; i++) {
            int age = sc.nextInt();
            int height = sc.nextInt();
            data[i] = new Student(age, height);
        }
        MinHeap minHeap = new MinHeap(data);
        for (int i = 0; i < n; i++) {
            Student student = minHeap.delete();
            System.out.println(student.age + " " + student.height);
        }
    }
}

class MinHeap {
    private Student[] data;
    int size;

    public MinHeap(Student[] d) {
        data = new Student[d.length];
        size = d.length - 1;
        for (int i = 1; i < d.length; i++) {
            data[i] = d[i];
        }
        for (int i = d.length / 2; i >= 1; i--) {
            makeHeap(i);
        }
    }

    private void makeHeap(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int target = i;
        if (left <= size && data[left].compareTo(data[target]) < 0) {
            target = left;
        }
        if (right <= size && data[right].compareTo(data[target]) < 0) {
            target = right;
        }
        if (target != i) {
            swap(i, target);
            makeHeap(target);
        }
    }

    private void swap(int i, int target) {
        Student temp = data[i];
        data[i] = data[target];
        data[target] = temp;
    }

    public Student delete() {
        Student root = data[1];
        swap(1, size--);
        makeHeap(1);
        return root;
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
        if (this.age == o.age) return this.height - o.height;
        return this.age - o.age;
    }


}
