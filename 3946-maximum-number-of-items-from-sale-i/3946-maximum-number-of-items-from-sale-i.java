class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int minPrice = Integer.MAX_VALUE;
        int[] dp = new int[budget + 1]; // dp[b] = max copies from first purchases costing at most b

        for (int i = 0; i < items.length; i++) {
            int factor = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);

            int value = 1; // the purchased copy itself
            for (int j = 0; j < items.length; j++) {
                if (j != i && items[j][0] % factor == 0) {
                    value++; // free copy of item j
                }
            }

            // 0/1 knapsack: the free copies are granted only once per item type
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + value);
            }
        }

        int best = 0;

        // Spend whatever is left on extra copies of the cheapest item
        for (int b = 0; b <= budget; b++) {
            best = Math.max(best, dp[b] + (budget - b) / minPrice);
        }

        return best;
    }
}