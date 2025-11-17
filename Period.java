import java.util.*;
public class Period
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String[] day = "Monday Tuesday Wednesday Thursday Friday Saturday".split(" ");
		int[] arr = new int[11];
		for(int i=0;i<6;i++)
		{
		    String[] str = sc.nextLine().replace("[","").replace("]"," ").split(" ");
		    int num = -1;
		    for(String ch : str)
		    {
		        String[] res = ch.split("-");
		        
		        if(res.length<2)
		        {
		            arr[Integer.parseInt(res[0])]++;
		        }
		        else
		        {
		            int s = Integer.parseInt(res[0]);
		            int e = Integer.parseInt(res[1]);
		            for(int k=s;k<=e;k++)
		            {
		                arr[k]++;
		            }
		        }
		    }
		    for(int j=1;j<arr.length;j++)
		    {
		        if(arr[j]==0 && j!=6)
		        {
		            num = j;
		        }
		    }
		    if(num!=-1)
		    {
				System.out.print(day[i]+" "+num);
		        return;
		    }
		}
		System.out.print("No Free Slots");
		sc.close();
	}
}