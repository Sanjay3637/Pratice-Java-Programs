import java.util.ArrayList;
import java.util.List;

public class findandreplace 
{
    public static List<String> findAndReplacePattern(String[] words, String pattern) 
    {
        ArrayList<String> list = new ArrayList<>();
        for(String ch : words)
        {
            boolean bol = true;
            // Arrays.fill(arr,0);
            int[] arr = new int[122];
            for(int i=0;i<ch.length();i++)
            {
                if((arr[ch.charAt(i)-'0']++)!=(arr[pattern.charAt(i)-'0']++))
                    bol = false;
                // arr[ch.charAt(i)-'0']++;
                // arr[pattern.charAt(i)-'0']++;
            }
            if(bol)
                list.add(ch);
        }
        return list;
    }
    public static void main(String[] args)
    {
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        System.out.print(findAndReplacePattern(words,pattern));
    }
}
