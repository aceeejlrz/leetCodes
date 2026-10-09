class Solution {
    public String longestPalindrome(String s) {
        int start = 0, maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            int oddLen  = expand(s, i, i);       // center on a character
            int evenLen = expand(s, i, i + 1);   // center between characters
            int len = Math.max(oddLen, evenLen);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    // Returns the length of the longest palindrome centered at (left, right)
    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}