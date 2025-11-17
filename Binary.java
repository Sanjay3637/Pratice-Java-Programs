import java.util.*;
public class Binary
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Integer.toBinaryString(n));
        StringBuilder sb = new StringBuilder();
        while(n>0)
        {
            sb.append(n%2);
            n /= 2;
        }
        sb.reverse();
        System.out.println("OUTPUT : " + sb.toString());
        sc.close();
    }
}