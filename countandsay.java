import java.util.*;
public class countandsay 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = "1";
        System.out.println(str);
        while(n-->1)
        {
            StringBuilder sb = new StringBuilder();
            int c = 1;
            for(int i=1;i<str.length();i++)
            {
                if(str.charAt(i)==str.charAt(i-1))
                    c++;
                else
                {
                    sb.append(c).append(str.charAt(i-1));
                    c = 1;
                }
            }
            sb.append(c).append(str.charAt(str.length()-1));
            str = sb.toString();
            System.out.println(str);
        }
        sc.close();
    }
}
