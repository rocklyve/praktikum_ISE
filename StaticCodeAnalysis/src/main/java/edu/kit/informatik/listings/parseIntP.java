package edu.kit.informatik.listings;
public class parseIntP {
	public boolean assignValue(String number) {
		try {
			int value = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}
		/* ... */
		return true;
	}
}