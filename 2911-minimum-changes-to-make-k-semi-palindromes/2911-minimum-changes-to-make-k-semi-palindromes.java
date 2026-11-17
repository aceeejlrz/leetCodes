class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = Integer.MAX_VALUE / 2;

        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                cost[i][j] = getCost(s, i, j, INF);
            }
        }

        // dp[c][j] = min changes to split the first j chars into c parts
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int c = 1; c <= k; c++) {
            for (int j = 1; j <= n; j++) {
                for (int i = 0; i < j; i++) {
                    if (dp[c - 1][i] >= INF || cost[i][j - 1] >= INF) {
                        continue;
                    }
                    dp[c][j] = Math.min(dp[c][j], dp[c - 1][i] + cost[i][j - 1]); // last part is s[i..j-1]
                }
            }
        }

        return dp[k][n];
    }

    // Returns the min changes to make s[l..r] a semi-palindrome over all valid divisors
    private int getCost(String s, int l, int r, int INF) {
        int len = r - l + 1;
        if (len == 1) {
            return INF; // single char has no valid divisor
        }

        int best = INF;
        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }

            int changes = 0;
            int m = len / d; // size of each group
            for (int g = 0; g < d; g++) {
                // Compare mirrored pairs inside the group starting at l + g
                for (int a = 0; a < m / 2; a++) {
                    int p = l + g + a * d;
                    int q = l + g + (m - 1 - a) * d;
                    if (s.charAt(p) != s.charAt(q)) {
                        changes++;
                    }
                }
            }
            best = Math.min(best, changes);
        }
        return best;
    }
}