import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];   // ASCII covers letters, digits, symbols, spaces
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (lastSeen[c] >= left) {   // duplicate is inside the current window
                left = lastSeen[c] + 1;
            }

            lastSeen[c] = right;
            best = Math.max(best, right - left + 1);
        }

        return best;
    }
}