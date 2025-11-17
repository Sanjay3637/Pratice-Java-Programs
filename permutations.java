import java.util.*;
public class permutations 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        if(n==1)
        {
            System.out.print(1);
        }
        else if(n==2 || n==3)
        {
            System.out.print("NO SOLUTION");
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            for(int i=2;i<=n;i+=2)
                sb.append(i).append(" ");
            for(int i=1;i<=n;i+=2)
                sb.append(i).append(" ");
            System.out.print(sb.toString().trim());
        }
        sc.close();
    }
}
