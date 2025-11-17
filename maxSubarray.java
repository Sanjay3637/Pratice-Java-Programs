import java.util.*;
public class maxSubarray 
{
    public static int[] max(int[] nums, int k) 
    {
        if (nums == null || k <= 0)
            return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) 
        {
            int maxVal = nums[i];
            for (int j = i; j < i + k; j++)
                maxVal = Math.max(maxVal, nums[j]);
            result[i] = maxVal;
        }
        return result;
    }

    public static void main(String[] args) 
    {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] ans = max(arr, k);
        System.out.println(Arrays.toString(ans));
    }
}
