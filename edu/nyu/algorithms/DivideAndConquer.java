/**
 * Maximum subarray implementation.
 * Strassen's algorithm for matrix multiplication.
 *
 */
package edu.nyu.algorithms;

import java.util.Arrays;

public class DivideAndConquer {

    public static void main(String[] args) {
	DivideAndConquer dac = new DivideAndConquer();
	//	int[] a = {Integer.MAX_VALUE, 2, Integer.MIN_VALUE};
	int[] a = {2, -1, 3, -2};
	Tuple max = dac.findMaximumSubarray(a, 0, a.length - 1);
	System.out.println(Arrays.toString(a));
	System.out.printf("max sum: %d, from %d to %d%n", max.sum, max.low, max.high);



	//matrix multiplication
	int[][] aa = {{1,0,0,0}, {0,1,0,0}, {0,0,1,0}, {4,3,2,1}};
	int[][] bb = {{1,2,3,4}, {5,6,7,8}, {4,3,2,1}, {8,7,6,5}};
	int[][] cc = dac.squareMatrixMultiply(aa, bb);
	dac.printSquareMatrix(aa);
	System.out.println("multiply by");
	dac.printSquareMatrix(bb);
	System.out.println("result: ");
	dac.printSquareMatrix(cc);
	System.out.println("result2: ");
	cc = dac.squareMatrixMultiplyRecursive(aa, bb);
	dac.printSquareMatrix(cc);
    }

    private static class Tuple {
	int low;
	int high;
	int sum;

	Tuple(int low, int high, int sum) {
	    this.low = low;
	    this.high = high;
	    this.sum = sum;
	}
    }

    private Tuple findMaxCrossingSubarray(int[] a, int low, int mid, int high) {
	if (a == null || a.length == 0) {
	    throw new IllegalArgumentException("Array null or empty");
	}
	int leftSum = Integer.MIN_VALUE;
	int sum = 0;
	int maxLeft = mid;
	for (int i = mid; i >= low; --i) {
	    sum += a[i];
	    if (sum > leftSum) {
		leftSum = sum;
		maxLeft = i;
	    }
	}

	int rightSum = Integer.MIN_VALUE;
	sum = 0;
	int maxRight = mid + 1;
	for (int j = mid + 1; j <= high; ++j) {
	    sum += a[j];
	    if (sum > rightSum) {
		rightSum = sum;
		maxRight = j;
	    }
	}
	
	return new Tuple(maxLeft, maxRight, leftSum + rightSum);
    }

    public Tuple findMaximumSubarray(int[] a, int low, int high) {
	if (a == null || a.length == 0) {
	    throw new IllegalArgumentException("Array null or empty");
	}
	if (low == high) {
	    return new Tuple(low, high, a[low]);
	} else {
	    int mid = (low + high) >>> 1;
	    Tuple left = findMaximumSubarray(a, low, mid);
	    Tuple right = findMaximumSubarray(a, mid + 1, high);
	    Tuple cross = findMaxCrossingSubarray(a, low, mid, high);
	    
	    if (left.sum >= right.sum && left.sum >= cross.sum) {
		return left;
	    } else if (right.sum >= left.sum && right.sum >= cross.sum) {
		return right;
	    } else {
		return cross;
	    }
	}
    }


    //brute force: O(n^3)
    public int[][] squareMatrixMultiply(int[][] a, int[][] b) {
	int n = a.length;
	int[][] c = new int[n][n];
	for (int i = 0; i < n; ++i) {
	    for (int j = 0; j < n; ++j) {
		c[i][j] = 0;
		for (int k = 0; k < n; ++k) {
		    c[i][j] += a[i][k] * b[k][j];
		}
	    }
	}
	return c;
    }

    //simple divide and conquer solution: O(n^3)
    public int[][] squareMatrixMultiplyRecursive(int[][] a, int[][] b) {
	int n = a.length;
	int half = n / 2;
	int[][] c = new int[n][n];
	if (n == 1) {
	    c[0][0] = a[0][0] * b[0][0];
	} else {
	    //TODO use index calculations.
	    int[][] a11 = new int[half][half];
	    int[][] a12 = new int[half][half];
	    int[][] a21 = new int[half][half];
	    int[][] a22 = new int[half][half];
	    int[][] b11 = new int[half][half];
	    int[][] b12 = new int[half][half];
	    int[][] b21 = new int[half][half];
	    int[][] b22 = new int[half][half];
	    int[][] c11 = new int[half][half];
	    int[][] c12 = new int[half][half];
	    int[][] c21 = new int[half][half];
	    int[][] c22 = new int[half][half];
	    partitionMatrix(a, a11, a12, a21, a22);
	    partitionMatrix(b, b11, b12, b21, b22);
	    partitionMatrix(c, c11, c12, c21, c22);
	    
	    matrixAdd(c11, squareMatrixMultiplyRecursive(a11, b11), 
		      squareMatrixMultiplyRecursive(a12, b21));
	    matrixAdd(c12, squareMatrixMultiplyRecursive(a11, b12), 
		      squareMatrixMultiplyRecursive(a12, b22));
	    matrixAdd(c21, squareMatrixMultiplyRecursive(a21, b11), 
		      squareMatrixMultiplyRecursive(a22, b21));
	    matrixAdd(c22, squareMatrixMultiplyRecursive(a21, b12), 
		      squareMatrixMultiplyRecursive(a22, b22));
	    for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
		    if (i < half && j < half) {
			c[i][j] = c11[i][j];
		    } else if (i < half && j >= half) {
			c[i][j] = c12[i][j - half];
		    } else if (i >= half && j < half) {
			c[i][j] = c21[i - half][j];
		    } else {
			c[i][j] = c22[i - half][j - half];
		    }
		}
	    }
	}
	return c;
    }

    private void partitionMatrix(int[][] m, int[][] m11, int[][] m12, int[][] m21, int[][] m22) {
	int n = m.length;
	int mid = n / 2;
	if (m11.length != mid || m12.length != mid || m21.length != mid || m22.length != mid) {
	    throw new IllegalArgumentException("Paritioned matrix size should be half of original matrix");
	}
	
	for (int i = 0; i < mid; ++i) {
	    for (int j = 0; j < mid; ++j) {
		m11[i][j] = m[i][j];
	    }
	    for (int j = mid; j < n; ++j) {
		m12[i][j - mid] = m[i][j];
	    }
	}
	for (int i = mid; i < n; ++i) {
	    for (int j = 0; j < mid; ++j) {
		m21[i - mid][j] = m[i][j];
	    }
	    for (int j = mid; j < n; ++j) {
		m22[i - mid][j - mid] = m[i][j];
	    }
	}
    }
    
    public void matrixAdd(int[][] sum, int[][] a, int[][] b) {
	if (sum == null || a == null || b == null ||
	    sum.length != a.length || a.length != b.length || sum.length != b.length) {
	    throw new IllegalArgumentException("Array(s) null or have unequal length");
	}
	int n = sum.length;
	for (int i = 0; i < n; ++i) {
	    for (int j = 0; j < n; ++j) {
		sum[i][j] = a[i][j] + b[i][j];
	    }
	}
    }
    
    public void printSquareMatrix(int[][] m) {
	int n = m.length;
	for (int i = 0; i < n; ++i) {
	    System.out.print("[");
	    for (int j = 0; j < n; ++j) {
		System.out.printf("%d", m[i][j]);
		if (j != n - 1) {
		    System.out.print(" ");
		}
	    }
	    System.out.println("]");
	}
    }

}