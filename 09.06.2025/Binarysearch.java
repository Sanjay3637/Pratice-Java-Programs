import java.util.*;
public class Binarysearch
{
	public static void main(String[] args) 
	{
	    Scanner sc = new Scanner(System.in);
		int[] arr = {1,3,5,7,9,11};
		int tar = sc.nextInt();
		sc.close();
		
		int s=0,e=arr.length-1;
		while(s<=e)
		{
		    int m = (s+e)/2;
		    if(arr[m]==tar)
		    {
		        System.out.print("Element found at index: "+m);
		        return;
		    }
		    if(arr[m]>tar)
		        e = m-1;
		    else
		        s = m+1;
		}
		System.out.print("Element not found in the array");
	}
}
