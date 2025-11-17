import java.util.*;
public class Repetitions 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int max = 1,c = 1;
        for (int i = 1; i < s.length(); i++) 
        {
            if (s.charAt(i) == s.charAt(i - 1)) 
            {
                c++;
                max = Math.max(max, c);
            } 
            else 
            {
                c = 1;
            }
        }
        System.out.print(max);
        sc.close();
    }
}
