import java.util.*;
public class twoknights 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=k;i++)
        {
            long s = i*i;
            long s1 = (s*(s-1))/2;
            long s2 = 4*(i-1)*(i-2);
            sb.append(s1-s2).append("\n");
        }
        System.out.print(sb.toString());
        sc.close();
    }
}
