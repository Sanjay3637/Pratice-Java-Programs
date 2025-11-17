import java.util.*;
public class evenMissingNumber 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        // for(int i=0;i<n-1;i++)
        // {
        //     if((arr[i+1]-arr[i])!=2)
        //     {
        //         System.out.print(arr[i]+2);
        //         return;
        //     }
        // }
        int num = arr[0];
        for(int i=0;i<n;i++)
        {
            if(arr[i]!=num)
            {
                System.out.print(num);
                return;
            }
            num += 2;
        }
        System.out.print(arr[n-1]+2);
        sc.close();
    }
}
