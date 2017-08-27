package com.hahogames.adventure;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Simple text adventure game. This class provides the entry point for running
 * the game.
 * 
 * @author paulh
 * @author harry
 */
public class Game {
	/**
	 * {@link Map} of {@code Place} objects keyed on {@code reference} in the
	 * configuration file
	 */
	private Map<String, Place> places = new HashMap<>();

	/**
	 * Collection of {@code Path} objects
	 */
	private List<Path> paths = new ArrayList<>();

	/**
	 * Game title
	 */
	private String title;

	/**
	 * Reference of starting {@link Place}
	 */
	private String start;

	/**
	 * Main
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.setup();
		game.play();
		return;
	}

	/**
	 * Plays the game. Currently this is just an infinite loop, there are no
	 * exit conditions for play.
	 */
	private void play() {
		System.out.println("Welcom to " + title + "!");
		Place current = places.get(start);
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println(current.description);
				if (current.items.size() > 0) {
					System.out.println("You see:");
					for (Item i : current.items) {
						System.out.println("* " + i.name() + ": "
								+ i.description());
					}
				}
				System.out.println();
				for (int i = 1; i <= current.paths.size(); i++) {
					System.out.println(i + ". "
							+ current.paths.get(i - 1).description);
				}
				int choice = scanner.nextInt();
				Path chosenPath = current.paths.get(choice - 1);
				current = chosenPath.to;
				System.out.println("---");
			}
		}
	}

	/**
	 * Sets up the game world from a configuration file.
	 */
	private void setup() {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(
					"game.xml");
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);

			NodeList newTitle = doc.getElementsByTagName("title");
			title = newTitle.item(0).getTextContent();
			NodeList newStart = doc.getElementsByTagName("start");
			start = newStart.item(0).getTextContent();

			NodeList newPlaces = doc.getElementsByTagName("place");
			for (int i = 0; i < newPlaces.getLength(); i++) {
				Node n = newPlaces.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					String ref = e.getElementsByTagName("reference").item(0)
							.getTextContent();
					String description = e.getElementsByTagName("description")
							.item(0).getTextContent();
					Place newPlace = new Place(description);
					places.put(ref, newPlace);
					NodeList items = ((Element) n).getElementsByTagName("item");
					for (int j = 0; j < items.getLength(); j++) {
						Node m = items.item(j);
						if (m.getNodeType() == Node.ELEMENT_NODE) {
							Element f = (Element) m;
							String name = f.getElementsByTagName("name")
									.item(0).getTextContent();
							String itemDescription = f
									.getElementsByTagName("description")
									.item(0).getTextContent();
							newPlace.addItem(new Item(name, itemDescription));
						}
					}
				}
			}

			NodeList newPaths = doc.getElementsByTagName("path");
			for (int i = 0; i < newPaths.getLength(); i++) {
				Node n = newPaths.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					String from = e.getElementsByTagName("from").item(0)
							.getTextContent();
					String to = e.getElementsByTagName("to").item(0)
							.getTextContent();
					String description = e.getElementsByTagName("description")
							.item(0).getTextContent();
					paths.add(new Path(places.get(from), places.get(to),
							description));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
