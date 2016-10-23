/**
 * Simulate HIRE-ASSISTANT process in the CLRS book.
 * Compare the origianl hiring algorithm and the randomized algorithm.
 */
package edu.nyu.algorithms;

import java.util.Arrays;
import java.util.Random;

public class HireAssistant {

    public static void main(String[] args) {
	HireAssistant ha = new HireAssistant();

	int[] candidateRanks;
	if (args.length == 0) {
	    candidateRanks = new int[]{2, 4, 6, 3, 9, 5, 7, 8};
	} else {
	    candidateRanks = new int[args.length];
	    for (int i = 0; i < args.length; ++i) {
		candidateRanks[i] = Integer.parseInt(args[i]);
	    }
	}
	System.out.printf("Rank order candidates come in: %s%n", Arrays.toString(candidateRanks));

	int numberOfHiredCandidates = ha.hire(candidateRanks);
	System.out.printf("Number of hire candidates: %d%n", numberOfHiredCandidates);
	
	int numberOfHiredCandidatesRandom = ha.randomizedHire(candidateRanks);
	System.out.printf("Randomized - Number of hire candidates: %d%n", numberOfHiredCandidatesRandom);


    }

    public int hire(int[] candidates) {
	int best = 0;//a least-qualified dummy candidate
	int numberOfHired = 0;
	for (int i = 0; i < candidates.length; ++i) {
	    //interview candidate i...
	    if (candidates[i] > best) {
		//hire candidate i
		best = candidates[i];
		numberOfHired += 1;
	    }
	}
	return numberOfHired;
    }

    public int randomizedHire(int[] candidates) {
	int[] randomized = Arrays.copyOf(candidates, candidates.length);
	shuffle(randomized);
	return hire(randomized);
    }
    private void shuffle(int[] array) {
	Random random = new Random();
	int n = array.length;
	for (int i = 0; i < n; ++i) {
	    int index = random.nextInt(n - i) + i;
	    int tmp = array[i];
	    array[i] = array[index];
	    array[index] = tmp;
	}
    }
}