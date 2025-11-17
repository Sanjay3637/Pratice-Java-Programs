package Recursion;
import java.util.*;
public class BinarySearch 
{
    public static int bin(int[] arr,int tar,int s,int e)
    {
        int m = (s+e)/2;
        if(s>e)
            return -1;
        if(arr[m]==tar)
            return m;
        if(arr[m]>tar)
            return bin(arr,tar,s,m-1);
        else
            return bin(arr,tar,m+1,e);
    }
    public static void main(String[] summa)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        int tar = sc.nextInt();
        sc.close();
        System.out.print("Target found at : "+bin(arr,tar,0,n-1));
    }
}
