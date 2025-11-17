import java.util.*;
public class evenlengthvowel
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        for(String ch : arr)
        {
            if(ch.length()%2==0)
            {
                if("aeiouAEIOU".indexOf(ch.charAt(0))!=-1)
                {
                    System.out.print(ch);
                    return;
                }
            }
        }
        System.out.print("OO");
        sc.close();
    }
}