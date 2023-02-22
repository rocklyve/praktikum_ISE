package edu.kit.informatik;

public class UnsafeCastBadExample {
    public void assignString(Object t){
        String value = (String) t;
        /* ... */
    }
}
