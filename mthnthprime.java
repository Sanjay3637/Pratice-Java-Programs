import java.util.*;
import java.math.*;
public class mthnthprime 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int c = n+m;
        int d = 2;
        ArrayList<Integer> list = new ArrayList<>();
        while(list.size()<c)
        {
            if(BigInteger.valueOf(d).isProbablePrime(10))
            {
                list.add(d);
            }
            d++;
        }
        c = 0;
        for(int i=0;i<=n;i++)
        {
            c += list.get(list.size()-1);
            list.remove(list.size()-1);
        }
        System.out.print(c);
        sc.close();

    }
}