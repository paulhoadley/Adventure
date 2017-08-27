package com.hahogames.adventure;

/**
 * An item that a player can use.
 * 
 * @author paulh
 */
public class Item {
	/**
	 * Name of this {@code Item}
	 */
	private final String name;

	/**
	 * Description for this {@code Item}
	 */
	private final String description;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            a name
	 * @param description
	 *            a description
	 */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
		return;
	}

	/**
	 * Returns name of this {@code Item}.
	 * 
	 * @return name
	 */
	public String name() {
		return name;
	}

	/**
	 * Returns description of this {@code Item}.
	 * 
	 * @return description
	 */
	public String description() {
		return description;
	}
}
