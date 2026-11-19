class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums); // sorted so extremes are at the ends
        int n = nums.length;

        // Change the two largest values
        int changeLargest = nums[n - 3] - nums[0];

        // Change the two smallest values
        int changeSmallest = nums[n - 1] - nums[2];

        // Change one smallest and one largest value
        int changeBoth = nums[n - 2] - nums[1];

        // Low score can always be made 0, so only the high score matters
        return Math.min(changeLargest, Math.min(changeSmallest, changeBoth));
    }
}