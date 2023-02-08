package edu.kit.informatik.listings;

public class parseIntN {
	public int foo(String number) {
		int value = Integer.parseInt(number);
		value++;

		return value;
	}

	public int foo2(String number) {
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = Integer.parseInt(number);
		}
		return value;
	}
}