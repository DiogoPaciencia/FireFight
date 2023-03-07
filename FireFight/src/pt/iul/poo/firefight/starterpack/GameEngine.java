package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class GameEngine implements Observer {

	// Dimensoes da grelha de jogo
	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;

	private ImageMatrixGUI gui; // Referencia para ImageMatrixGUI (janela de interface com o utilizador)
	private static GameEngine INSTANCE;
	private static final String FILE = "levels/level1.txt";
	private String playerName;
	private Controller controller;
	
	private GameEngine() {

		gui = ImageMatrixGUI.getInstance(); // 1. obter instancia ativa de ImageMatrixGUI
		gui.setSize(GRID_HEIGHT, GRID_WIDTH); // 2. configurar as dimensoes
		gui.registerObserver(this); // 3. registar o objeto ativo GameEngine como observador da GUI
		gui.go(); // 4. lancar a GUI
		controller = Controller.getInstance();
		
	}

	public static GameEngine getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GameEngine();
		}
		return INSTANCE;
	}

	@Override
	public void update(Observed s1ource) {	
		int key = gui.keyPressed(); // obtem o codigo da tecla pressionada
		
		if (key == KeyEvent.VK_P && controller.getPlane() == null) {	// se a tecla for ENTER, manda o bombeiro mover
			controller.pPressed();	
		} 
		
		if (key == KeyEvent.VK_ENTER) {	// se a tecla for ENTER, manda o bombeiro mover-se
			controller.enterPressed();
		} 
		
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			controller.arrowsPressed(key);
		}
		controller.updateScore();
		finishGame();
		gui.update(); // redesenha as imagens na GUI, tendo em conta as novas posicoes
	}

	// Criacao dos objetos e envio das imagens para GUI
	public void start(String playerName) {
		try {
			controller.setPlayerName(playerName);
			controller.setLevel(1);
			controller.createMap(FILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sendImagesToGUI(); // enviar as imagens para a GUI
		gui.update();
	}
	
	private void sendImagesToGUI() {
		gui.addImages(controller.getTileList());
	}
	
	public void finishGame () {
		
		if(controller.getFires().size() == 0) {
			controller.highscore();
			gui.setMessage("Acabaste o nível.\nA tua pontuação é " + controller.getScore() + " pontos");
			controller.changeMovable();
			controller.clearList();
			controller.setLevel(controller.getLevel()+1);
			controller.changeMap();
		} 
	}
}