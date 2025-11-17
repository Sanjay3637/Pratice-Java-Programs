import java.util.*;
public class dynamic 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        arr[0] = 1;
        for(int i=1;i<=n;i++)
        {
            for(int j=i;j<=n;j++)
            {
                arr[j] += arr[j-i];
            }
        }
        System.out.print(arr[n]);
        sc.close();
    }
}
