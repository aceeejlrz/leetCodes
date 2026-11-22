class Solution {
    public boolean checkString(String s) {
        // "ba" appearing anywhere means some 'b' comes before an 'a'
        return !s.contains("ba");
    }
}