import java.util.*;
public class two 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = sc.nextInt();
        sc.close();
        String[] word = ("Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven, Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen, Eighteen, Nineteen, Twenty, Twenty-one, Twenty-two, Twenty-three, Twenty-four, Twenty-five, Twenty-six, Twenty-seven, Twenty-eight, Twenty-nine, Thirty, Thirty-one, Thirty-two, Thirty-three, Thirty-four, Thirty-five, Thirty-six, Thirty-seven, Thirty-eight, Thirty-nine, Forty, Forty-one, Forty-two, Forty-three, Forty-four, Forty-five, Forty-six, Forty-seven, Forty-eight, Forty-nine, Fifty, Fifty-one, Fifty-two, Fifty-three, Fifty-four, Fifty-five, Fifty-six, Fifty-seven, Fifty-eight, Fifty-nine, Sixty, Sixty-one, Sixty-two, Sixty-three, Sixty-four, Sixty-five, Sixty-six, Sixty-seven, Sixty-eight, Sixty-nine, Seventy, Seventy-one, Seventy-two, Seventy-three, Seventy-four, Seventy-five, Seventy-six, Seventy-seven, Seventy-eight, Seventy-nine, Eighty, Eighty-one, Eighty-two, Eighty-three, Eighty-four, Eighty-five, Eighty-six, Eighty-seven, Eighty-eight, Eighty-nine, Ninety, Ninety-one, Ninety-two, Ninety-three, Ninety-four, Ninety-five, Ninety-six, Ninety-seven, Ninety-eight, Ninety-nine, One hundred").split(", ");
        int vow = 0;
        for(int ch : arr) 
        {
            String currentWord = word[ch];
            for(char c : currentWord.toCharArray()) 
            {
                if("aeiouAEIOU".indexOf(c) != -1)
                    vow++;
            }
        }
        int sum = 0;
        for(int i=0; i<n-1; i++) 
        {
            for(int j=i+1; j<n; j++)
            {
                if(arr[i] + arr[j] == vow)
                    sum++;
            }
        }
        System.out.print(word[sum]);
    }
}