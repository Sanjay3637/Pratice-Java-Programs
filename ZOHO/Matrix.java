package ZOHO;
import java.util.*;
public class Matrix 
{
    static final int N = 3; 
    static boolean[][] atom = new boolean[N + 1][N + 1];
    static char[][] board = new char[N + 1][N + 1];
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        for (int i = 0; i < A; i++) 
        {
            int r = sc.nextInt();
            int c = sc.nextInt();
            atom[r][c] = true;
        }
        for (int r = 1; r <= N; r++) 
        {
            for (int c = 1; c <= N; c++) 
                board[r][c] = atom[r][c] ? 'X' : '-';
        }
        int R = sc.nextInt();
        for (int i = 0; i < R; i++) 
        {
            String token = sc.next();
            simulateRay(token);
        }
        sc.close();
        printBoard();
    }
    private static void simulateRay(String token) 
    {
        int r = 0, c = 0;
        int dr = 0, dc = 0; 
        if (token.charAt(0) == 'R') 
        { 
            r = Integer.parseInt(token.substring(1));
            c = 0;
            dr = 0; dc = 1; 
        } 
        else if (token.charAt(0) == 'C') 
        {
            r = N + 1;
            c = Integer.parseInt(token.substring(1));
            dr = -1; dc = 0; 
        }
        while (true) 
        {
            r += dr;
            c += dc;
            if (r < 1 || r > N || c < 1 || c > N) 
                return;
            if (atom[r][c]) 
            {
                board[r][c] = '-';
                // if we want we can print 'H' instead of '-'
                return;
            }
        }
    }
    private static void printBoard() 
    {
        for (int r = N; r >= 1; r--) 
        {
            for (int c = 1; c <= N; c++)
                System.out.print(board[r][c] + " ");
            System.out.println();
        }
    }
}
