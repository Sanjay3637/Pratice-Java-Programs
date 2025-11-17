import java.util.*;

public class one 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        char[] arr = sc.nextLine().toCharArray();
        sc.close();
        for(int i=0;i<n;i++)
        {
            if(arr[i]=='-')
            {
                int nearA = -1;
                for(int k=i-1;k>=0;k--)
                {
                    if(arr[k]=='A')
                    {
                        nearA = k;
                        break;
                    }
                }
                int nearB = -1;
                for(int k=i+1;k<n;k++)
                {
                    if(arr[k]=='B')
                    {
                        nearB = k;
                        break;
                    }
                }
                int dA = 0;
                int dB = 0;
                if(nearA!=-1)
                    dA = i-nearA;
                else
                    dA = Integer.MAX_VALUE;
                if(nearB!=0)
                    dB = nearB-i;
                else
                    dB = Integer.MAX_VALUE;
                if(dA < dB)
                    arr[i] = 'A';
                else if(dA > dB)
                    arr[i] = 'B';
            }
        }
        int cA = 0,cB = 0;
        for(char ch : arr)
        {
            if(ch=='A')
                cA++;
            else
                cB++;
        }
        System.out.print((cA>cB)?'A':(cA<cB)?'B':"Coalition government");
    }
}

// 14
// --AB--AB---A--