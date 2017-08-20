package com.hahogames.adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a location in the game.
 * 
 * @author paulh
 */
public class Place {
	/**
	 * This location's text description
	 */
	public final String description;

	/**
	 * List of {@link Path}s for which this {@code Place} is an endpoint
	 */
	public List<Path> paths = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param description
	 *            text description of this {@code Place}
	 */
	public Place(String description) {
		this.description = description;
		return;
	}

	/**
	 * Adds an outgoing {@link Path} for this {@code Place}.
	 * 
	 * @param path
	 *            a {@link Path}
	 */
	public void addOutgoingPath(Path path) {
		paths.add(path);
		return;
	}
}
