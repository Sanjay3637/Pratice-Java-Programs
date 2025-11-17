package Recursion;
import java.util.*;
public class RemoveOccurrenceOfX 
{
    public static String rem(String str) 
    {
        if (str.isEmpty())
            return str;
        if (str.charAt(0) == 'x')
            return rem(str.substring(1));
        else 
            return str.charAt(0) + rem(str.substring(1));
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Before remove : ");
        String str = sc.nextLine();
        sc.close();
        System.out.println("After removing 'x': " + rem(str));
    }
}