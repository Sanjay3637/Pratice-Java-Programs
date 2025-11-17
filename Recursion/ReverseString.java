package Recursion;
import java.util.*;
public class ReverseString 
{
    public static String rev(String str)
    {
        if(str.length()<=1)
            return str;
        return rev(str.substring(1))+str.charAt(0);
    }
    public static void main(String[] summa)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Before Reverse : ");
        String str = sc.nextLine();
        sc.close();
        System.out.print("After Reverse : ");
        System.out.print(rev(str));
    }
}
