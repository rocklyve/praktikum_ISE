package edu.kit.informatik.listings;

import java.util.List;

public class wrongLoopTypePII {
	public void printList(List<String> list) {
		for (var item : list) {
			System.out.println(item);
		}
	}
}