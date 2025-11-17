import java.util.*;
public class unique 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        sc.close();
        int[] arr = new int[a.length];
        for(int i=0;i<arr.length;i++)
            arr[i] = Integer.parseInt(a[i]);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ch : arr)
            map.put(ch,map.getOrDefault(ch,0)+1);
        HashSet<Integer> set = new HashSet<>();
        for(Map.Entry<Integer,Integer> ch : map.entrySet())
        {
            if(!set.add(ch.getValue()))
            {
                System.out.print("false");
                return;
            }
            set.add(ch.getValue());
        }
        System.out.print("true");
        sc.close();
    }
}
