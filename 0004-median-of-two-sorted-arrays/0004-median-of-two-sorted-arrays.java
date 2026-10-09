class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Binary search the shorter array so j can never go out of range
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int half = (m + n + 1) / 2;   // left side gets the extra element when total is odd
        int lo = 0, hi = m;

        while (lo <= hi) {
            int i = (lo + hi) / 2;    // elements taken from nums1
            int j = half - i;         // elements taken from nums2

            int left1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int left2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }
                return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
            } else if (left1 > right2) {
                hi = i - 1;           // took too many from nums1
            } else {
                lo = i + 1;           // took too few from nums1
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted");
    }
}