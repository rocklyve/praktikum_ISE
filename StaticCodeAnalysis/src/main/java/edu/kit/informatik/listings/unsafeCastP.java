
package edu.kit.informatik.listings;

import java.util.List;

public class unsafeCastP {
	public void assignString(Object t) {
		if (t instanceof String) {
			String value = (String) t;
			/* ... */
		}
	}
}