import java.util.*;
public class Swap
{
    public static void main(String[] args)
    {
        ArrayList<Integer> neg = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();
        int[] arr = {1,2,3,4,5,6};
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<0)
                neg.add(arr[i]);
            else
                pos.add(arr[i]);
        }
        int c=0;
        while(!pos.isEmpty() || !neg.isEmpty())
        {
            if(!pos.isEmpty())
            {
                arr[c++] = pos.get(0);
                pos.remove(0);
            }
            if(!neg.isEmpty())
            {
                arr[c++] = neg.get(0);
                neg.remove(0);
            }
        }
        System.out.print(Arrays.toString(arr));
    }
}