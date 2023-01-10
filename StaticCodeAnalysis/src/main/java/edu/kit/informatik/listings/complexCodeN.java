package edu.kit.informatik.listings;

private static Collection<String> getAllStudentsLectures(List<Student> students, Lecture... lectures) {
    List<Lecture> searchLectures = Arrays.stream(lectures == null || lectures.length == 0 ? Lecture.values() : lectures).collect(Collectors.toList());
    return students == null || students.isEmpty() ? Collections.emptyList() : students.stream().filter(s -> searchLectures.contains(s.getLecture())).flatMap(s -> s.getLectures().stream()).distinct().collect(Collectors.toList());
}
