package edu.kit.informatik.listings;


public class wrongLoopTypeNIII {
	public void foo() {
		/* ... */
		String input = getInput();
		for (;checkInput(input);) {
			input = getInput();
		}
		/* ... */
	}

	private boolean checkInput(String input) {
		return true;
	}
	private String getInput() {
		return "";
	}

}