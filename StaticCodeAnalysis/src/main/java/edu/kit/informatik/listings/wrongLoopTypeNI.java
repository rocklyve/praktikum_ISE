package edu.kit.informatik.listings;

public class wrongLoopTypeNI {
	public void foo() {
		/* ... */
		int i = 0;

		while (i < 10) {
			System.out.println(i);
			i++;
		}
		/* ... */
	}
}