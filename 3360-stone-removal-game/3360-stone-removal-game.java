class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10; // stones required for the current turn
        boolean aliceTurn = true;

        while (n >= stones) {
            n -= stones;
            stones--; // next player removes one fewer
            aliceTurn = !aliceTurn;
        }

        return !aliceTurn; // whoever is stuck loses
    }
}