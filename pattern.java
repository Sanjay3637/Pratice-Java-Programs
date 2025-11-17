import java.util.*;

public class pattern 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder("1"); 
        list.add(sb.toString()); 
        while (list.size() < n) 
        {
            String curr = sb.toString();
            StringBuilder res = new StringBuilder();
            int count = 1;
            for (int i = 1; i < curr.length(); i++) 
            {
                if (curr.charAt(i) == curr.charAt(i - 1)) 
                {
                    count++;
                } 
                else 
                {
                    res.append(count).append(curr.charAt(i - 1));
                    count = 1;
                }
            }
            res.append(count).append(curr.charAt(curr.length() - 1));
            sb = new StringBuilder();
            sb.append(res.toString());
            list.add(sb.toString());
        }
        System.out.println(list);
        sc.close();
    }
}
