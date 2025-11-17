import java.util.*;
public class Rank 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int[] a = new int[arr.length];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            a[i] = Integer.parseInt(arr[i]);
            if(!list.contains(a[i]))
                list.add(a[i]);
        }
        int[] res = new int[a.length];
        Collections.sort(list);
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<list.size();j++)
            {
                if(a[i]==list.get(j))
                    res[i] = j+1;
            }
        }
        System.out.print(Arrays.toString(res));
        sc.close();
    }
}
