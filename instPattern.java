import java.util.*;
public class instPattern 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        int num = 1;
        for(int i=1;i<=n;i++)
        {
            int val = num;
            for(int j=1;j<=i;j++)
            {
                System.out.print((val)+" ");
                val -= (n-j);
            }
            num += (n-i);
            System.out.println();
        }
    }
}
