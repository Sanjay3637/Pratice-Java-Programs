import java.util.*;
public class commonheights 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int[] arr = new int[a.length];
        sc.close();
        for(int i=0;i<arr.length;i++)
            arr[i] = Integer.parseInt(a[i]);
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<arr.length-1;i++)
        {
            if(arr[i]<0 || arr[arr.length-1]<0)
            {
                System.out.print("Not valid inputs");
                return;
            }
            if(!set.add(arr[i+1]-arr[i]))
            {
                System.out.print(arr[i+1]-arr[i]);
                return;
            }
            set.add(arr[i+1]-arr[i]);
        }
    }
}
