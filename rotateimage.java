// import java.util.*;
// public class rotateimage
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);
//         String str = sc.nextLine();
//         int n = 0;
//         for(int i = 1; i < str.length(); i++)
//         {  
//             if(str.charAt(i)=='[') 
//             {
//                 n++;  
//             }
//         }
//         str = str.replaceAll("\\[","");
//         str = str.replaceAll("\\]","");
//         String[] s = str.split(",");
//         int[] arr = new int[n*n];
//         int c  = 0;
//         for(String ch : s)
//         {
//             arr[c++] = Integer.parseInt(ch);
//         }
//         c=0;
//         int[][] max = new int[n][n];
//         for(int i=0;i<n;i++)
//         {
//             for(int j=0;j<n;j++)
//             {
//                 max[i][j] = arr[c++];
//             }
//         }
//         StringBuilder sb = new StringBuilder();
//         sb.append("[");
//         for(int i=0;i<n;i++)
//         {
//             sb.append("[");
//             for(int j=n-1;j>=0;j--)
//             {
//                 sb.append(String.valueOf(max[j][i]));
//                 if(j!=0)
//                     sb.append(",");
//             }
//             sb.append("]");
//             if(i!=n-1)
//                 sb.append(",");
//         }
//         sb.append("]");
//         System.out.print(sb.toString());
//         sc.close();
//     }
// }

import java.util.*;
public class rotateimage
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int n = matrix.length;
        int[][] arr = new int[n][n];
        int r = 0;
        for(int i=0;i<n;i++)
        {
            int c = 0;
            for(int j=n-1;j>=0;j--)
            {
                arr[c++][r] = matrix[i][j];
            }
            r++;
        }
        r=0;
        for(int i=n-1;i>=0;i--)
        {
            int c=0;
            for(int j=n-1;j>=0;j--)
            {
                matrix[r][c++] = arr[i][j];
            }
            r++;
        }
        sc.close();
    }
}