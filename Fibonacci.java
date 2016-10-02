/**
 * 3 implementaions of calculating nth value of Fibonacci sequence to compare their efficiency.
 * Fibonacci:
 * fib(0) = 0
 * fib(1) = 1
 * fib(n) = fib(n - 1) + fib(n - 2)
 *
 * runIterativeMethod is supposed to be the fastest.
 * runRecursiveMethod is supposed to be the most intuitive but slowest.
 * runMemoizeMethod use an array to memo-ize calculated results to speed up calculation.
 * 
 * This program takes two arguments to run. 
 * It outputs the nth Fibonnaci value and the running time it takes.
 * for example:
 * ./Fibonnaci i 10 // calculate 10th Fibonacci value using iterativeMethod 
 * ./Fibonnaci r 10 // calculate 10th Fibonacci value using recursiveMethod 
 * ./Fibonnaci m 10 // calculate 10th Fibonacci value using memoizeMethod
 * 
 * Created by Zebin Xu 
 * zebinxu@nyu.edu
 **/
public class Fibonacci {

    private int runningTime;
    private long[] dictionary;

    public Fibonacci() {
	runningTime = 0;
    }

    public int getRunningTime() {
	return runningTime;
    }

    public long runIterativeMethod(int n) {
	runningTime = 0;
	if (n < 0) {
	    throw new RuntimeException("n < 0");
	} else if (n == 0) {
	    return 0;
	} else if (n == 1) {
	    return 1;
	}
		
	long[] adder = new long[] {0, 1};
	runningTime += 1;

	long temp;
	for (int i = 1; i < n; ++i) {
	    temp = adder[0];
	    adder[0] = adder[1];
	    adder[1] = adder[1] + temp;
	    runningTime += 4;
	}
	return adder[1];
    }

    public long runRecursiveMethod(int n) {
	if (n < 0) {
	    throw new RuntimeException("n < 0");
	}

	if (n == 0) {
	    return 0;
	} else if (n == 1) {
	    runningTime += 1;
	    return 1;
	} else {
	    runningTime += 3;
	    return runRecursiveMethod(n - 1) + runRecursiveMethod(n - 2);
	}
    }

    public long runMemoizeMethod(int n) {
	if (n <= 0) {
	    return 0;
	}

	if (dictionary == null) {
	    runningTime = 0;
	    dictionary = new long[160];//automatically initialized to 0
	    dictionary[0] = 0;
	    dictionary[1] = 1;
	}

	if (dictionary[n] == 0) {
	    runningTime += 4;
	    dictionary[n] = runMemoizeMethod(n - 1) + runMemoizeMethod(n - 2);
	}
	runningTime += 1;
	return dictionary[n];
    }

    private static long handleInput(String[] args, Fibonacci fib) {
	if (args.length != 2) {
	    throw new RuntimeException("the number of argument should be 2.");
	}
	long fibResult = 0;

	switch(args[0]) {
	case "i":
	case "I":
	    fibResult = fib.runIterativeMethod(Integer.parseInt(args[1]));
	    break;

	case "r":
	case "R":
	    fibResult = fib.runRecursiveMethod(Integer.parseInt(args[1]));
	    break;

	case "m":
	case "M":
	    fibResult = fib.runMemoizeMethod(Integer.parseInt(args[1]));
	    break;
	default:
	    fibResult = 0;
	    break;
	}
	return fibResult;
    }

    public static void main(String[] args) {
	Fibonacci fib = new Fibonacci();

	try {
	    long fibResult = handleInput(args, fib);
	    System.out.println(fibResult);
	    System.out.printf("runningTime= %d%n", fib.getRunningTime());
	} catch(RuntimeException e) {
	    System.out.printf("Error: %s%n", e.getMessage());
	}

    }
}