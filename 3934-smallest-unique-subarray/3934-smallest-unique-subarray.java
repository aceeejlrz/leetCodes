class Solution {
    private static final long MOD = (1L << 61) - 1; // Mersenne prime keeps collisions negligible

    public int smallestUniqueSubarray(int[] nums) {
        long base = 200_000 + (long) (Math.random() * 1_000_000_000L); // random base resists anti-hash tests
        int low = 1;
        int high = nums.length; // the whole array is always unique

        // If length L has a unique subarray, so does L + 1, so binary search works
        while (low < high) {
            int mid = (low + high) / 2;

            if (hasUnique(nums, mid, base)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Returns true if some subarray of the given length occurs exactly once
    private boolean hasUnique(int[] nums, int length, long base) {
        long power = 1; // base^(length - 1)
        for (int i = 1; i < length; i++) {
            power = multiply(power, base);
        }

        Map<Long, Integer> count = new HashMap<>(); // window hash -> occurrences
        long hash = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i >= length) {
                hash = (hash - multiply(nums[i - length], power) + MOD) % MOD; // drop the leftmost element
            }
            hash = (multiply(hash, base) + nums[i]) % MOD; // append the new element

            if (i >= length - 1) {
                count.merge(hash, 1, Integer::sum);
            }
        }

        return count.containsValue(1);
    }

    // Returns (a * b) mod (2^61 - 1) without overflow
    private long multiply(long a, long b) {
        long high = Math.multiplyHigh(a, b);
        long low = a * b;
        long result = (low & MOD) + ((low >>> 61) | (high << 3));

        return result >= MOD ? result - MOD : result;
    }
}