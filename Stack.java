import java.util.*;

public class Stack {
    static int[] arr = new int[5];
    static int f = 0, b = 0;
    static {
        Arrays.fill(arr, -1);
    }

    public static void pop() {
        if (b == f) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Poped Successfully : " + arr[--b]);
        arr[b] = -1;
    }

    public static void push(int n) {
        if (b == arr.length) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[b++] = n;
        System.out.println("Pushed Successfully : " + n);
    }

    public static void Empty() {
        if (f == b) {
            System.out.println("Stack is Empty ? : true");
            return;
        }
        System.out.println("Stack is Empty ? : false");
    }

    public static void peek() {
        if (f == b) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Peek at stack : " + arr[f]);
    }

    public static void main(String[] args) {
        push(10);
        push(20);
        push(30);
        pop();
        peek();
        Empty();
        System.out.println(Arrays.toString(arr));
    }
}
