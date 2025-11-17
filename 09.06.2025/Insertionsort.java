import java.util.*;
public class Insertionsort 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        sc.close();
        System.out.println("Before Insertion sort : "+Arrays.toString(arr));
        for(int i=1;i<n;i++)
        {
            for(int j=i;j>0;j--)
            {
                if(arr[j]<arr[j-1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
           
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("After Insertion sort : "+Arrays.toString(arr));
    }
}
