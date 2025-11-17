import java.util.*;
public class knapSack 
{
    public static int knapsack(int[] val, int[] wt, int W) 
    {
        int N = val.length;
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 0; i <= N; i++) 
        {
            for (int w = 0; w <= W; w++) 
            {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(dp[i - 1][w], val[i - 1] + dp[i - 1][w - wt[i - 1]]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[N][W];
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String[] profitInput = sc.nextLine().replace("{","").replace("}","").split(",");
        int[] val = new int[profitInput.length];
        for (int i = 0; i < profitInput.length; i++)
            val[i] = Integer.parseInt(profitInput[i]);
        String[] weightInput = sc.nextLine().replace("{","").replace("}","").split(",");
        int[] wt = new int[weightInput.length];
        for (int i = 0; i < weightInput.length; i++)
            wt[i] = Integer.parseInt(weightInput[i]);
        int W = sc.nextInt();
        sc.close();
        int result = knapsack(val, wt, W);
        System.out.println(result);
    }
}