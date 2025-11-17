package Recursion;
import java.util.*;
public class Sumofdigits 
{
    public static int sumOf(int n)
    {
        if(n==0)
            return 0;
        return n%10 + sumOf(n/10);
    }
    public static void main(String[] summa)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Number : ");
        int n = sc.nextInt();
        System.out.print("Sum of digits : " + sumOf(n));
        sc.close();
    }
}
