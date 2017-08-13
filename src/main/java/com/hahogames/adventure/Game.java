package com.hahogames.adventure;

import java.util.Scanner;

/**
 * Simple text adventure game.
 * 
 * @author paulh
 * @author harry
 */
public class Game {
	public static void main(String[] args) {
		System.out.println("Starting up!");
		Game game = new Game();
		Place start = game.setup();
		game.play(start);
		return;
	}

	private void play(Place start) {
		Place current = start;
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

	private Place setup() {
		Place kitchen = new Place("You are in a kitchen.");
		Place attic = new Place("It is very dark up here.");
		Place garden = new Place("You are outside in a nice garden.");

		Path ladderUp = new Path(kitchen, attic, "There is a rickety ladder going up.");
		Path ladderDown = new Path(attic, kitchen, "There is a ladder but it doesn't look safe!");
		Path doorOut = new Path(kitchen, garden, "There is a wooden door heading outside.");
		Path doorIn = new Path(garden, kitchen, "A white door looks like it leads inside.");
		
		return kitchen;
	}
}
