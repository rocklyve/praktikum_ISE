package edu.kit.informatik.listings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Encapsulation {
    /**
     * Second, let us discuss encapsulation. What is fine to expose via a getter?
     */

    // If returned, list is exposed directly and mutable, this is NOT good:
    List<String> arrayList = new ArrayList<>();
    List<String> linkedList = new LinkedList<>();
    Set<String> set = new HashSet<>();
//    List<String> fromStream = Stream.of(...).

//    collect(toList()); // in theory no guarantees on mutability

    // List is immutable, can be returned:
//    List<String> fromStream2 = Stream.of(...).

//    toList();

//    List<String> quickList = List.of(...);
    List<String> immutableCopy = Collections.unmodifiableList(arrayList);
    List<String> immutableCopy2 = List.copyOf(arrayList);

    // List is mutable, but returning an internal copy is fine:
    List<String> mutableCopy = new ArrayList<>(arrayList);
    Set<String> mutableSetCopy = new HashSet<>(set);
}