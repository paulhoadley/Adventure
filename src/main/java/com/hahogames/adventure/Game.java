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
 * Simple text adventure game.
 * 
 * @author paulh
 * @author harry
 */
public class Game {
	private Map<String, Place> places = new HashMap<>();
	private List<Path> paths = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Starting up!");
		Game game = new Game();
		game.setup();
		game.play();
		return;
	}

	private void play() {
		Place current = places.get("A");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(current.description);
			for (int i = 1; i <= current.paths.size(); i++) {
				System.out.println(i + ". " + current.paths.get(i - 1).description);
			}
			int choice = scanner.nextInt();
			Path chosenPath = current.paths.get(choice - 1);
			current = chosenPath.to;
			System.out.println("---");
		}
	}

	private void setup() {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("places.xml");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList newPlaces = doc.getElementsByTagName("place");
			for (int i = 0; i < newPlaces.getLength(); i++) {
				Node n = newPlaces.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					String ref = e.getElementsByTagName("reference").item(0).getTextContent();
					String description = e.getElementsByTagName("description").item(0).getTextContent();
					places.put(ref, new Place(description));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("paths.xml");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList newPaths = doc.getElementsByTagName("path");
			for (int i = 0; i < newPaths.getLength(); i++) {
				Node n = newPaths.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					String from = e.getElementsByTagName("from").item(0).getTextContent();
					String to = e.getElementsByTagName("to").item(0).getTextContent();
					String description = e.getElementsByTagName("description").item(0).getTextContent();
					paths.add(new Path(places.get(from), places.get(to), description));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}
