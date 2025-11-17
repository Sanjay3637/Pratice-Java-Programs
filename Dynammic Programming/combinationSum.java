import java.util.*;

public class combinationSum {
    public static List<Integer> shortestCombinationSum(int[] numbers, int targetSum) {
        @SuppressWarnings("unchecked")
        List<Integer>[] dp = new ArrayList[targetSum + 1];
        dp[0] = new ArrayList<>();
        for (int i = 0; i <= targetSum; i++) {
            if (dp[i] != null) {
                for (int num : numbers) {
                    if (i + num <= targetSum) {
                        if (dp[i + num] == null || dp[i].size() + 1 < dp[i + num].size()) {
                            List<Integer> newCombination = new ArrayList<>(dp[i]);
                            newCombination.add(num);
                            dp[i + num] = newCombination;
                        }
                    }
                }
            }
        }
        if (dp[targetSum] != null) {
            Collections.sort(dp[targetSum]);
            return dp[targetSum];
        } else
            return new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int targetSum = sc.nextInt();
        sc.nextLine();
        String[] numbersInput = sc.nextLine().split(",");
        sc.close();
        int[] numbers = new int[numbersInput.length];
        for (int i = 0; i < numbersInput.length; i++)
            numbers[i] = Integer.parseInt(numbersInput[i]);
        List<Integer> result = shortestCombinationSum(numbers, targetSum);
        if (result.isEmpty())
            System.out.println(-1);
        else {
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + ",");
            }
        }
    }
}
