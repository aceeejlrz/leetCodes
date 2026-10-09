class Solution {
    public boolean isPalindrome(int x) {
        // Negatives fail; so does anything ending in 0 (except 0 itself)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        // Even digit count: halves match exactly
        // Odd digit count: drop the middle digit from rev
        return x == rev || x == rev / 10;
    }
}