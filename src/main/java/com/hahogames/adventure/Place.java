package com.hahogames.adventure;

import java.util.ArrayList;
import java.util.List;

public class Place {
	public final String description;

	public List<Path> paths = new ArrayList<>();

	public Place(String description) {
		this.description = description;
		return;
	}

	public void addOutgoingPath(Path path) {
		paths.add(path);
		return;
	}
}
