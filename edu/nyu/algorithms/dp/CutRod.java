/**
 * Rod Cutting implementation in chapter 15 in the CLRS book.
 */
public class CutRod {
    public static void main(String[] args) {
        CutRod cr = new CutRod();
        int[] prices = { 1, 5, 8, 9 };
        int max = cr.cutRodRecursive(prices, 4);
        System.out.printf("max: %d%n", max);
    }
 
    //Recursive top-down implementation
    public int cutRodRecursive(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            q = Math.max(q, prices[i] + cutRodRecursive(prices, n - i - 1));
        }
        return q;
    }

}