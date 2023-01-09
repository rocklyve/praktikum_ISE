package edu.kit.informatik;

import java.util.Date;
import java.util.List;

public class UnsafeCastBadExample {
    public void assignString(Object t){
        String value = (String) t;
        /* ... */
    }
}
