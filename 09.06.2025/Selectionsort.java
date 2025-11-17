import java.util.*;

public class Selectionsort 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        sc.close();
        System.out.println("Before Selection sort : "+Arrays.toString(arr));
        int c = 0;
        for(int i=0;i<n-1;i++)
        {
            int minIn = Integer.MAX_VALUE;
            int min = Integer.MAX_VALUE;
            for(int j=c;j<n;j++)
            {
                if(min>arr[j])
                {
                    min = arr[j];
                    minIn = j;
                }
            }
            int temp = arr[c];
            arr[c] = arr[minIn];
            arr[minIn] = temp;
            c++;
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("After Selection sort : "+Arrays.toString(arr));
    }
}
