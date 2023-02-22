package edu.kit.informatik.listings;

import static org.apache.commons.lang3.ArrayUtils.isSorted;

import java.util.Random;

public class clumsySolutionN {
	public int[] foo(int[] toSort){
		Random r = new Random();
		while (!isSorted(toSort)) { //check if isSorted
			int a = r.nextInt(toSort.length);
			int b = r.nextInt(toSort.length);
			int temp = toSort[a];
			toSort[a] = toSort[b];
			toSort[b] = temp;
		}
		return toSort;
	}

}

