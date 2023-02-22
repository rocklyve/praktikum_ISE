
package edu.kit.informatik.listings;

public class unsafeCastP {
	public void assignString(Object t) {
		if (t instanceof String) {
			String value = (String) t;
			/* ... */
		}
	}
}