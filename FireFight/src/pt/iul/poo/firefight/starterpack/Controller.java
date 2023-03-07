package pt.iul.poo.firefight.starterpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Controller {

	private static Controller INSTANCE;
	private int score = 0;	
	private String playerName;
	private int level;
	private List<ImageTile> tileList; // Lista de imagens
	private Fireman fireman; // Referencia para o bombeiro
	private Controllable bulls; //Referencia para o bulldozer
	private Plane plane = null;
	private boolean firemanOnBull = false;

	private Controller() {
		tileList = new ArrayList<>();	
	}

	public List<ImageTile> getTileList() {
		return tileList;
	}

	public Controllable getBulls() {
		return bulls;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public static Controller getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Plane getPlane() {
		return plane;
	}
	
	public void clearList() {
		tileList.clear();
	}
	
	public void changeScore(int x) {
		if(score + x < 0) {
			score = 0;
			
		}else {
			score += x;	
		
		}
	}	
	
	public void updateScore() {
		ImageMatrixGUI.getInstance().setStatusMessage("Pontuação: " + score);
		
	}

	public void highscore() {
		
		try {
			ArrayList<String> scores = retrieveScores();
			scores = addScore(scores);
			writeScores(scores);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Lê os scores
	public ArrayList<String> retrieveScores() throws IOException{
		
		File file = new File("highscores_level" + level + ".txt");
		file.createNewFile();
		
		Scanner reader = new Scanner(file);
		ArrayList<String> scores = new ArrayList<>();
		
		while(reader.hasNextLine()) {
			String aux = reader.nextLine();
			scores.add(aux);
		}
		
		reader.close();
		return scores;
		
	}
	
	public ArrayList<String> addScore(ArrayList<String> scores) {
		
		int i = 0;
		boolean foundPosition = false;

		for(String x : scores) {
			if(!foundPosition) {
				if(this.score <= Integer.parseInt(x.split(":")[1])) {
					i++;
					continue;
					

				}else {
					foundPosition = true;
				}
			}
		}
		
		scores.add(i, this.playerName + ":" + this.score);
		
		return scores;
		
	}
	
	public void writeScores(ArrayList<String> scores) throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter(new File("highscores_level" + level + ".txt"));
		int aux = 0;
		for(int i = 0; i <= scores.size()-1 && aux!=5; i++) {
			writer.println(scores.get(i));
			aux++;
		}
		
		writer.close();
		
	}
	
	public void changeMap() {
		ImageMatrixGUI.getInstance().clearImages();
		try {
			createMap("levels/level" +level+ ".txt");
		} catch (FileNotFoundException e) {
			ImageMatrixGUI.getInstance().setStatusMessage("");
			ImageMatrixGUI.getInstance().setMessage("Acabaste o jogo!");
			System.exit(0);
			e.printStackTrace();
		}
		score = 0;
		ImageMatrixGUI.getInstance().setStatusMessage("Pontuação: " +score);
	}
	
	public void createMap(String file) throws FileNotFoundException {
		Scanner s = new Scanner(new File(file));
		int countLines = 0;
		
		while(s.hasNextLine()) {
			String str = s.nextLine();
			
			if( countLines < ImageMatrixGUI.getInstance().getGridDimension().height) {
				for(int i = 0; i < str.length(); i++) {
					tileList.add(GameElement.createObject(String.valueOf(str.charAt(i)), new Point2D(i, countLines)));
				}
				countLines ++;
				
			}else {
				String[] oi = str.split(" ");
				ImageTile i = GameElement.createObject(oi[0], new Point2D(Integer.parseInt(oi[1]), Integer.parseInt(oi[2])));
				if(oi[0].equals("Fireman")) {
					fireman= ((Fireman)i);
					
				}
				if(oi[0].equals("Bulldozer")) {
					bulls= ((Bulldozer)i);
					
				}
				tileList.add(i);
				countLines ++;
			}
		}
		ImageMatrixGUI.getInstance().addImages(tileList);
		s.close();
		initiateFire();
		
	}
	
	public Controllable getControllable() {
		
		for(ImageTile obj : tileList) {
			if(obj instanceof Controllable) {
				if(fireman.getPosition().equals(obj.getPosition()) && !obj.getName().equals("fireman")){
					bulls = (Controllable) obj;
					return (Controllable) obj;
				}
			}
		}
		return null;
		
	}
	
	public void checkBurnt() {
		
		List<ImageTile> aux = new ArrayList<>();
		
		for(ImageTile obj: tileList) {
			if(obj instanceof Burnable) {
				if(((Burnable) obj).getBurning()) {
					aux.add(obj);
				}
			}
		}
				
		for(ImageTile obj: aux) {
			if(obj.getName().equals("fuelbarrel"))	
				((FuelBarrel) obj).burnt();
			else
				((Burnable) obj).burnt();
		}
	}
	
	public void moveBot() {
		ArrayList<ImageTile> bots = getBots();
		for(ImageTile bot: bots) {
			if(bot.getName().equals("plane")){
				movePlane();
			} else {
				((Bot)bot).move();
			}
		}
	}
	
	public ArrayList<ImageTile> getBots(){
		ArrayList<ImageTile> aux = new ArrayList<>();
		for(ImageTile obj: tileList) {
			if(obj instanceof Bot) {
				aux.add(obj);
			}
		}
		return aux;
	}
	
	public ImageTile existentBurnt(Point2D pos) {
		
		for(ImageTile obj: tileList) {
			if(obj.getPosition().equals(pos)) {
				if(obj instanceof Burnt) {
					return obj;
				}
			}
		}
		return null;		
	}
	
	public ImageTile existentBurnable(Point2D pos) {
		
		for(ImageTile obj: tileList) {
			if(obj.getPosition().equals(pos)) {
				if(obj instanceof Burnable) {
					return obj;
				}
			}
		}
		return null;		
	}
	
	public Fire existentFire(Point2D pos) {
		
		for(ImageTile obj: tileList) {
			if(obj.getPosition().equals(pos)) {
				if(obj instanceof Fire) {
					return (Fire)obj;
				}
			}
		}
		return null;		
	}
	
	public List<ImageTile> getFires(){
		
		List<ImageTile> fires = new ArrayList<>();
		
		for(ImageTile obj: tileList) {
			if(obj instanceof Fire) {
				fires.add(obj);
			}
		}
		return fires;
	}
	
	private void startSpread(List<ImageTile> fires) {
		
		for(ImageTile obj: fires) {
				((Fire) obj).spreadTo();
			
		}
	}
	
	// Adicionar mais objetos 
		public void addObject(ImageTile i) {
			tileList.add(i);
			ImageMatrixGUI.getInstance().addImage(i);
			
		}
		
	//Remove objetos
	public void removeObject (ImageTile i) {
		tileList.remove(i);
		ImageMatrixGUI.getInstance().removeImage(i);	
	}
	
	public void changeMovable() {
		
		if(firemanOnBull == true) { 
			fireman.setPosition(bulls.getPosition());
			addObject(fireman);
			firemanOnBull = false;
			
		} else {
			ImageMatrixGUI.getInstance().removeImage(fireman);
			removeObject(fireman);
		}		
	}
	
	public void initiateFire () {
		
		List<ImageTile> fires = getFires();
		
		for(ImageTile fire: fires) {
			for(ImageTile obj: tileList) {
				if(fire.getPosition().equals(obj.getPosition()) && obj instanceof Burnable ) {
					((Burnable) obj).setFire();
				}
				
			}
		}
	}
	
	public void movePlane() {
		
		if(plane != null) {
			plane.move();
			if(plane.getPosition().getY()<0) {
				removeObject(plane);
				plane = null;
			}
		}
		
	}
	
	public Point2D getFiremanPos () {
		return fireman.getPosition();
	}
	
	public Point2D getBullPos() {
		return bulls.getPosition();
	}
	
	public void enterPressed() {
		if(firemanOnBull == true) 
			changeMovable();
		ImageMatrixGUI.getInstance().update();

	}
	
	public void arrowsPressed(int key) {
		if(firemanOnBull == false) { 
			fireman.action(key);
			moveBot();
			checkBurnt();
			startSpread(getFires());

			if(getControllable() != null) {
					changeMovable();
					firemanOnBull = true;		
			}
		}else {
			if(bulls.getName().equals("bulldozer")) {
				((Bulldozer)bulls).action(key);
			}
			else if(bulls.getName().equals("firetruck")) {
				((FireTruck)bulls).action(key);
			}
			moveBot();
			checkBurnt();
		
			startSpread(getFires());

		}
		ImageMatrixGUI.getInstance().update();

	}
	
	public void pPressed() {
		int initialX = 0;
		int max = 0;
		
		for(int i=0; i< ImageMatrixGUI.getInstance().getGridDimension().width; i++) {
			int aux = 0;
			for(ImageTile obj: tileList) {
				if(obj instanceof Fire && obj.getPosition().getX() == i) {
					aux++;
				}
				if(aux > max) {
					initialX = i;
					max = aux;
				}
			}
		}
		
		if(max>0) {
			plane = new Plane(new Point2D(initialX, ImageMatrixGUI.getInstance().getGridDimension().height-1));
			addObject(plane);
			Fire fire = existentFire(plane.getPosition());
			if(fire != null) {
				ImageTile obj = existentBurnable(plane.getPosition());
				((Burnable)obj).putOut();
				removeObject(fire);
				ImageMatrixGUI.getInstance().removeImage(fire);
			}
		}
		ImageMatrixGUI.getInstance().update();
	}
}

