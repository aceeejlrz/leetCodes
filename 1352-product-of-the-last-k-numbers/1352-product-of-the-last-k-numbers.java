class ProductOfNumbers {

    private List<Integer> prefix; // prefix[i] = product of numbers since the last zero, up to i

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1); // sentinel so the first division works
    }

    public void add(int num) {
        // A zero wipes out every product that spans it, so reset
        if (num == 0) {
            prefix = new ArrayList<>();
            prefix.add(1);
            return;
        }

        // Extend the running product
        prefix.add(prefix.get(prefix.size() - 1) * num);
    }

    public int getProduct(int k) {
        int n = prefix.size();

        // Window reaches back past the last zero
        if (k >= n) {
            return 0;
        }

        // Product of last k = total / product before the window
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */