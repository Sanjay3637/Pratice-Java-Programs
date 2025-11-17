import java.util.*;
public class minCoinChange 
{
    public static int minCoins(int[] coins, int amount) 
    {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); 
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) 
        {
            for (int coin : coins) 
            {
                if (coin <= i) 
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        int tar = sc.nextInt();
        sc.close();
        System.out.print(minCoins(arr,tar));
    }   
}
