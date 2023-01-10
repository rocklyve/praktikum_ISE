/** First, let us discuss mutablity, meaning the ability to modify a list (add & remove elements). */

    // Mutable, content can be changed:
    List<String> arrayList = new ArrayList<>();
    List<String> linkedList = new LinkedList<>();
    Set<String> set = new HashSet<>();
    List<String> mutableCopy = new ArrayList<>(arrayList);
    Set<String> mutableSetCopy = new HashSet<>(set);
    List<String> fromStream = Stream.of(...).collect(toList()); // in theory no guarantees on mutability

    // Immutable, content CANNOT be changed:
    List<String> fromStream2 = Stream.of(...).toList();
    List<String> quickList = List.of(...);
    List<String> immutableCopy = Collections.unmodifiableList(arrayList);
    List<String> immutableCopy2 = List.copyOf(arrayList);