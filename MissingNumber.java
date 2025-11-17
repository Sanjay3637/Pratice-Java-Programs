import java.util.*;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long sum = 0;
        for(int i=0;i<n-1;i++)
        {
            sum += sc.nextLong();
        }
        long total = (long)(n * (n + 1)) / 2;
        System.out.print(total - sum);
        sc.close();
    }
}