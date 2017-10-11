package zeroSumSubsequence;

import java.util.ArrayList;
import java.util.List;

public class ZeroSumSubsequence {

	private static ArrayList<Integer> lengthValues = new ArrayList<Integer>();

	/**
	 * Finds the maximum length of sequences that sum to zero
	 * 
	 * @param sequence of integers which may or may not add up to zero
	 * @return maximum length/number of integers whose summation is zero,
	 * 		   or zero if no such sequence is possible
	 */
	
	public static int getMaximumLength(List<Integer> sequence) {

		List<Integer> reducedSequence = new ArrayList<Integer>();
		boolean positiveFlag = false, negativeFlag = false, zeroFlag = false;
		int sum = 0, length = 0, zeroCount = 0;
		
		// Check the base case that only zero integers sum to zero
		for (Integer i : sequence) {
			if (i > 0) {
				positiveFlag = true;
			}
			if (i < 0) {
				negativeFlag = true;
			}
			if (i == 0) {
				zeroFlag = true;
				zeroCount++;
			}
			sum += i;
		}

		// Want to skip rest of code if the sum equals to zero
		if (sum == 0) {
			// Add the length of the current sequence to an ArrayList to use
			// for comparisons later
			lengthValues.add(sequence.size());
		} else {
			// Identifying zero values as only possible answers to the zero sum sequence
			if (positiveFlag == true && negativeFlag == false || positiveFlag == false && negativeFlag == true) {
				if (zeroFlag == true) {
					return zeroCount;
				}
			} else {
				// If there are both positive and negative integers, use recursion to get to a point
				// where either we find a group of integers that sum to zero, or no such group exists
				if (sequence.isEmpty() == false) {
					for (int i = 0; i < sequence.size(); i++) {
						reducedSequence = new ArrayList<Integer>(sequence);
						reducedSequence.remove(i);
						getMaximumLength(reducedSequence);
					}
				}
			}
		}
		
		// Use a helper method to find the maximum length of sequences
		length = findHighestLength(lengthValues);
		return length;
	}

	/**
	 * Finds the maximum length of sequences in all possible valid combinations
	 * 
	 * @param lengthList - ArrayList of all the possible sequence lengths that
	 * 		               sum up to zero
	 * @return the maximum length in the ArrayList, or zero if it is empty
	 */
	public static int findHighestLength(ArrayList<Integer> lengthList) {
		int length = 0;
		if (lengthList.isEmpty() == false) {
			for (Integer i : lengthList) {
				if (i > length) {
					length = i;
				}
			}
		}

		return length;
	}

}
