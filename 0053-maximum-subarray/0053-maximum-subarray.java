class Solution {
    public int maxSubArray(int[] nums) {
        int best = nums[0]; // largest sum seen so far
        int current = nums[0]; // best sum of a subarray ending at the current index

        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray or start fresh at i
            current = Math.max(nums[i], current + nums[i]);

            // Update the overall best
            best = Math.max(best, current);
        }

        return best;
    }
}