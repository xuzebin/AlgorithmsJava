package edu.nyu.algorithms.dp;

public class CutRod {
    public static void main(String[] args) {
	CutRod cr = new CutRod();
	int[] prices = { 1, 5, 8 };
	int max = cr.recursive(prices, 3);
	System.out.printf("max: %d%n", max);
    }
    
    public int recursive(int[] prices, int n) {
	if (n == 0) {
	    return 0;
	}
	int q = Integer.MIN_VALUE;
	for (int i = 0; i < prices.length; ++i) {
	    q = Math.max(q, prices[i] + recursive(prices, n - i));
	}
	return q;
    }
}