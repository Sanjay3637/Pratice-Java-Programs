import java.util.*;

public class AntiClockWiseMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int top = 0, bottom = row - 1, left = 0, right = col - 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (top <= bottom && left <= right) {
            for (int i = top; i <= bottom; i++) {
                list.add(arr[i][left]);
            }
            left++;
            for (int i = left; i <= right; i++) {
                list.add(arr[bottom][i]);
            }
            bottom--;
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(arr[i][right]);
                }
                right--;
            }
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(arr[top][i]);
                }
                top++;
            }
        }
        for (int ch : list)
            System.out.print(ch + " ");
        sc.close();
    }
}