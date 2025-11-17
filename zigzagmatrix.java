import java.util.*;
public class zigzagmatrix 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] mat = new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                mat[i][j] = sc.nextInt();
        for(int i=0;i<row;i++)
        {
            if(i%2!=0)
            {
                int s = 0, e = mat[i].length-1;
                while(s<e)
                {
                    int temp = mat[i][s];
                    mat[i][s] = mat[i][e];
                    mat[i][e] = temp;
                    s++;
                    e--;
                }
            }
        }
        System.out.println("OUTPUT -> ");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        sc.close();
    }
}
