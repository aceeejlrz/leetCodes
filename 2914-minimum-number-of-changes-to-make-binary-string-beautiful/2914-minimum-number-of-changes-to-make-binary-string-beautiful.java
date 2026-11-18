class Solution {
    public int minChanges(String s) {
        int changes = 0; // number of mismatched pairs

        // Each even-aligned pair must hold equal characters
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }

        return changes;
    }
}