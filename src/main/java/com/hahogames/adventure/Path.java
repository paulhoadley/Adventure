package com.hahogames.adventure;

public class Path {
	public final Place from;
	
	public final Place to;

	public final String description;

	public Path(Place from, Place to, String description) {
		this.from = from;
		from.addOutgoingPath(this);
		this.to = to;
		this.description = description;
		return;
	}
}
