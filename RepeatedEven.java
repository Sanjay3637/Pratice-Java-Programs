import java.util.*;
public class RepeatedEven 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max = 0;
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
            max = Math.max(max,arr[i]);
        }
        int[] res = new int[max+1];
        for(int ch : arr)
            res[ch]++;
        boolean bol = false;
        for(int i=1;i<max+1;i++)
        {
            if(res[i]%2==0)
            {
                System.out.print(i+" ");
                bol = true;
            }
        }
        if(!bol)
            System.out.print("-1");
        sc.close();
    }
}
