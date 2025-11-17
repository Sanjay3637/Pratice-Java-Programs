import java.util.*;
public class Array
{
	public static void main(String[] args) 
	{
		int[] arr = {6,3,4,7,2,3};
		int[] res = new int[arr.length];
		for(int i=0;i<arr.length;i++)
		{
		    int s=i,e=i;
		    int sum = 0,sum2 = 0;
		    while(e<arr.length-1)
		    {
		        e++;
		        sum = Math.abs(sum+arr[e]);
		    }
		    while(s>=0)
		    {
		        s--;
		        if(s!=-1)
		            sum2 = sum2+arr[s];
		    }
		    res[i] = Math.abs(sum-sum2);
		}
		System.out.print(Arrays.toString(arr));
	}
}
