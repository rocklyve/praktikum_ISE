package edu.kit.informatik.listings;

public class wrongLoopTypePIII {
	public void foo() {
		/* ... */
		String input;
		do {
			input = getInput();
		} while (checkInput(input));
		/* ... */
	}

	private String getInput() {
		return "";
	}

	private boolean checkInput(String input) {
		return true;
	}
}