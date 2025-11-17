import java.util.*;
public class Mergesort 
{
    public static int[] mergeSort(int[] arr)
    {
        if(arr.length==1)
            return arr;
        int mid = arr.length/2;
        int[] left = mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));
        int[] res = merge(left,right);
        return res;
    }
    public static int[] merge(int[] left,int[] right)
    {
        int[] join = new int[left.length+right.length];
        int i=0,j=0,k=0;
        while(i<left.length &&j<right.length)
        {
            if(left[i]<right[j])
                join[k++] = left[i++];
            else
                join[k++] = right[j++];
        }
        while(i<left.length)
            join[k++] = left[i++];
        while(j<right.length)
            join[k++] = right[j++];        
        System.out.println(Arrays.toString(join));
        return join;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        sc.close();
        System.out.println("Before Merge sort : "+Arrays.toString(arr));
        System.out.println("After Merge sort : "+Arrays.toString(mergeSort(arr)));
    }
}
