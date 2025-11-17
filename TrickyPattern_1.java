import java.util.*;
public class TrickyPattern_1
{
	public static void main(String[] args) 
	{
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    int num = 1;
	    for(int i=0;i<n;i++)
	    {
	        for(int j=n-1;j>i;j--)
	        {
	            System.out.print(" ");
	        }
	        for(int j=0;j<=i;j++)
	        {
	            System.out.print(num++);
	        }
	        for(int j=0;j<i;j++)
	        {
	            System.out.print(num-(j+2));
	        }
	        System.out.println();
	        num -= i;
	    }
        sc.close();
	}
}
