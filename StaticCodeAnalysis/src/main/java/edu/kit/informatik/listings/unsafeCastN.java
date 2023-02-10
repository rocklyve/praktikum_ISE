
package edu.kit.informatik.listings;

import java.util.List;

public class unsafeCastN {
	public String assignString(Object t) {
		String value = (String) t;
		return value;
		/* ... */
	}
}