class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int best = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j]; // running product
                g = gcd(g, nums[j]); // running gcd
                l = l / gcd(l, nums[j]) * nums[j]; // running lcm

                // lcm * gcd is at most 2520 * 10, and prod never shrinks, so stop early
                if (prod > 25200) {
                    break;
                }

                if (prod == (long) l * g) {
                    best = Math.max(best, j - i + 1);
                }
            }
        }

        return best;
    }

    // Returns the greatest common divisor of a and b
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}