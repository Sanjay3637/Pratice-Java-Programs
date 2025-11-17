package Recursion;
import java.util.*;
public class Palindrome 
{
    public static boolean pal(String str,int s,int e)
    {
        if(s>=e)
            return true;
        if(str.charAt(s)!=str.charAt(e))
            return false;
        return pal(str,s+1,e-1);
    }
    public static void main(String[] summa)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Is Palindrome ? : ");
        String str = sc.nextLine();
        sc.close();
        System.out.print(pal(str,0,str.length()-1));
    }
}
