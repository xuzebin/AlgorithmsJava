/**
 * Rod Cutting implementation in chapter 15 in the CLRS book.
 * It includes:
 * Naive implementation using recursion O(2^n)
 * DP solution using top-down approach: memoize recursive algorithm
 * DP solution using bottom-up approach: solve the subproblems in size order (smallest subproblems first).
 * 
 */
public class CutRod {
    public static void main(String[] args) {
        CutRod cr = new CutRod();
        int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

        for (int i = 0; i < prices.length + 1; i++) {
            int max0 = cr.cutRodRecursive(prices, i);
            int max1 = cr.memoizedCutRod(prices, i);
            int max2 = cr.bottomUpCutRod(prices, i);
            System.out.printf("recursive max: %d%n", max0);
            System.out.printf("memoized max: %d%n", max1);
            System.out.printf("bottomUp (dp) max: %d%n", max2);
            System.out.println("----------------");
        }
    }
 
    //Naive version: recursive top-down implementation
    //Time: O(2^n)
    public int cutRodRecursive(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, prices[i] + cutRodRecursive(prices, n - i - 1));
        }
        return max;
    }

    //Dynamic-programming: top-down approach with memoization
    //Time: O(n^2)
    public int memoizedCutRod(int[] prices, int n) {
        int[] revs = new int[n + 1];//revs[i] corresponds to the maximum revenues of length i. We define revs[0] = 0.
        for (int i = 0; i < revs.length; i++) {
            revs[i] = -1;//we use -1 here to indicate a state that the revs is not cached yet instead of negative infinity in the book because revenue is always nonnegative.
        }
        return memoizedCutRodAux(prices, n, revs);
    }

    private int memoizedCutRodAux(int[] prices, int n, int[] revs) {
        if (revs[n] >= 0) {
            return revs[n];
        }
        int max = Integer.MIN_VALUE;
        if (n == 0) {
            max = 0;
        } else {
            for (int i = 0; i < n; i++) {
                max = Math.max(max, prices[i] + memoizedCutRodAux(prices, n - i - 1, revs));
            }
        }
        revs[n] = max;
        return max;
    }
    
    //Dynamic-programming: bottom-up approach
    //Time: O(n^2)
    public int bottomUpCutRod(int[] prices, int n) {
        int[] revs = new int[n + 1];
        for (int i = 0; i < revs.length; i++) {
            revs[i] = 0;//no harm to initialize all revs instead of only revs[0] to 0.
        }
        int max = Integer.MIN_VALUE;
        for (int j = 1; j <= n; j++) {//revs[j] indicates maximum revenue of a rod of length j.
            max = Integer.MIN_VALUE;
            for (int i = 0; i < j; i++) {
                max = Math.max(max, prices[i] + revs[j - i - 1]);
            }
            System.out.printf("revMax[%d]= %d%n", j, max);
            revs[j] = max;
        }
        return revs[n];
    }
}