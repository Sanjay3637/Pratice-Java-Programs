import java.util.*;
public class OddPyramid
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c=1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {
                System.out.print("  ");
            }
            for(int j=0;j<(2*i)+1;j++)
            {
                System.out.print(c + " ");
                c+=2;
            }
            System.out.println();
        }
        sc.close();
    }
}