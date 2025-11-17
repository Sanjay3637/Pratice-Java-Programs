import java.util.*;
public class Queue
{
    static int[] arr = new int[5];
    static int r = 0,f = 0;
    static 
    {
        Arrays.fill(arr,-1);
    }
    public static void push(int n)
    {
        if(r>=5)
        {
            System.out.println("Queue is full");
            return;
        }
        arr[r++] = n;
    }
    public static void Empty()
    {
        if(f==r)
        {
            System.out.println("true");
            return;
        }
        else
        {
            System.out.println("false");
            return;
        }
    }
    public static void pop()
    {
        if(r==f)
        {
            System.out.println("Queue is empty");
            return;
        }
        for(int i=0;i<r-1;i++)
        {
            arr[i] = arr[i+1];
        }
        arr[--r] = -1;
    }
	public static void main(String[] args) 
	{
		push(10);
	    pop();
// 		push(20);
// 		push(30);
// 		push(40);
// 		push(50);
// 		pop();
		Empty();
		System.out.println(Arrays.toString(arr));
	}
}
