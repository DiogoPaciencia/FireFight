package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class FiremanBot extends GameElement implements Bot {

	public FiremanBot(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "firemanbot";
	}	

	@Override
	public int getLayer() {
		return 2;
	}	
	
	public void move() {
		
		Direction randDir = Direction.random();
		Point2D pos = getPosition().plus(randDir.asVector());
		
		if(ImageMatrixGUI.getInstance().isWithinBounds(pos)) {
		
			setPosition(pos);
			Fire fire = Controller.getInstance().existentFire(getPosition());
			Water water = null;

			if(fire != null) {
				
				if(randDir.equals(Direction.DOWN)) {
					water = new Water(pos, "water_down");
					
				}
				if(randDir.equals(Direction.UP)) {
					water = new Water(pos, "water_up");
					
				}
				if(randDir.equals(Direction.LEFT)) {
					water = new Water(pos, "water_left");
								}
				if(randDir.equals(Direction.RIGHT)) {
					water = new Water(pos, "water_right");
					
				}
				ImageTile obj = Controller.getInstance().existentBurnable(getPosition());
				((Burnable) obj).putOut();
				water.sendWater((Fire)fire);
				Controller.getInstance().changeScore(1);
			}
		}
	}
}

