package edu.kit.informatik.listings;

import java.util.ArrayList;
import java.util.List;

public class AssertIf {
    private List<String> memberNames = new ArrayList<>();

    public void addMember(String name) {
        assert (name != null && !name.isBlank());
        memberNames.add(name);
    }

    public void addMember2(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid member name!");
        }
        memberNames.add(name);
    }
}