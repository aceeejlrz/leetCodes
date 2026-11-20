class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums); // process values in increasing order

        int[] dp = new int[1_000_003]; // dp[v] = longest consecutive run ending at value v
        int best = 0;

        for (int x : nums) {
            // Raise x to x + 1 (done first so it uses the old dp[x])
            dp[x + 1] = dp[x] + 1;

            // Keep x as is
            dp[x] = dp[x - 1] + 1;

            best = Math.max(best, Math.max(dp[x], dp[x + 1]));
        }

        return best;
    }
}