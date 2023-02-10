package edu.kit.informatik.listings;
public class parseIntP {
	public int assignValue(String number) {
		int value = 0;
		try {
			value = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return value;
		}
		/* ... */
		return value;
	}
}