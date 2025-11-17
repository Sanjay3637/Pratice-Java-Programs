import java.util.*;
public class Numberofmoves 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        sum /= n;
        for(int i=0;i<n;i++)
        {
            if(sum<arr[i])
            {
                sum = arr[i]-sum;
            }
        }
        System.out.print(sum);
        sc.close();
    }   
}
