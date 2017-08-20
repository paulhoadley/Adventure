package com.hahogames.adventure;

/**
 * <p>
 * Represents a directed path from a {@link Place} to another {@code Place}.
 * {@code Path}s are directed so that we can:
 * </p>
 * 
 * <ul>
 * <li>Provide different descriptions from the different end points; and</li>
 * <li>Make one-way {@code Path}s between {@link Place}s.</li>
 * 
 * @author paulh
 *
 */
public class Path {
	/**
	 * Origin {@link Place}
	 */
	public final Place from;

	/**
	 * Destination {@link Place}
	 */
	public final Place to;

	/**
	 * Text description for this {@code Path}
	 */
	public final String description;

	/**
	 * Constructor
	 * 
	 * @param from
	 *            origin {@link Place}
	 * @param to
	 *            destination {@link Place}
	 * @param description
	 *            text description
	 */
	public Path(Place from, Place to, String description) {
		this.from = from;
		from.addOutgoingPath(this);
		this.to = to;
		this.description = description;
		return;
	}
}
