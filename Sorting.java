import java.util.*;
public class Sorting
{
    public static void bubbleSort(int[] arr)
    {
        for(int i=0;i<arr.length-1;i++)
        {
            for(int j=0;j<arr.length-1-i;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("Pass : "+i+"-> "+Arrays.toString(arr));
        }
    }
    public static void selectionSort(int[] arr)
    {
        for(int i=0;i<arr.length-1;i++)
        {
            int min = i;
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[min])
                    min = j;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
            System.out.println("Pass : "+i+"-> "+Arrays.toString(arr));
        }
    }
	public static void insertionSort(int[] arr) 
	{
	    for(int i=1;i<arr.length;i++)
	    {
	        int key = arr[i];
	        int j=i-1;
	        while(j>=0 && arr[j]>key)
	        {
	                arr[j+1] = arr[j];
	                j--;
	        }
	        arr[j+1] = key;
	        System.out.println("Pass : "+(i-1)+"-> "+Arrays.toString(arr));
	    }
	}
	public static void main(String[] args)
	{
	    int[] arr1 = {4,3,2,10,12,1,5,6};
	    System.out.println("Bubble Sort : ");
	    bubbleSort(arr1);
	    System.out.println();
	    int[] arr2 = {4,3,2,10,12,1,5,6};
	    System.out.println("Selection Sort : ");
	    selectionSort(arr2);
	    System.out.println();
	    int[] arr3 = {4,3,2,10,12,1,5,6};
	    System.out.println("Insertion Sort : ");
	    insertionSort(arr3);
	}
}
