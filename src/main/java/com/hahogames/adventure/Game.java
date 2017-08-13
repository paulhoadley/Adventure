package com.hahogames.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple text adventure game.
 * 
 * @author paulh
 * @author harry
 */
public class Game {
	private List<Place> places = new ArrayList<>();
	private List<Path> paths = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Starting up!");
		Game game = new Game();
		game.setup();
		game.play();
		return;
	}

	private void play() {
		Place current = places.get(0);
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
		// Places
		Place kitchen = new Place("You are in a kitchen.");
		places.add(kitchen);
		Place attic = new Place("It is very dark up here.");
		places.add(attic);
		Place garden = new Place("You are outside in a nice garden.");
		places.add(garden);
		// Paths
		paths.add(new Path(kitchen, attic, "There is a rickety ladder going up."));
		paths.add(new Path(attic, kitchen, "There is a ladder but it doesn't look safe!"));
		paths.add(new Path(kitchen, garden, "There is a wooden door heading outside."));
		paths.add(new Path(garden, kitchen, "A white door looks like it leads inside."));
		return;
	}
}
