package Recursion;
import java.util.*;
public class ReplacePI 
{
    public static String repl(String str)
    {
        if(str.length()<2)
            return str;
        if(str.substring(0,2).equals("PI"))
            return "3.14" + repl(str.substring(2));
        return str.charAt(0) + repl(str.substring(1));
    }
    public static void main(String[] summa)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Before replace : ");
        String str = sc.nextLine();
        sc.close();
        System.out.print("After replace : "+ repl(str));
    }
}
