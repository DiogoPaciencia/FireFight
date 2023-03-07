package pt.iul.poo.firefight.starterpack;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// Cria uma instancia de GameEngine e depois inicia o jogo
		// Podera' vir a ficar diferente caso defina GameEngine como solitao
		
		System.out.println("Introduza o nickname");
		Scanner scanner = new Scanner(System.in);
		String playerName = scanner.next();
		scanner.close();
		GameEngine game = GameEngine.getInstance();
		game.start(playerName);
		
	}
}
