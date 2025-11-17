import java.util.*;
public class Bubblesort 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        sc.close();
        System.out.println("Before Bubble sort : "+Arrays.toString(arr));
        for(int i=0;i<n-1;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    arr[j] ^= arr[j+1];
                    arr[j+1] ^= arr[j];
                    arr[j] ^= arr[j+1];
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("After Bubble sort : "+ Arrays.toString(arr));
    }
}
