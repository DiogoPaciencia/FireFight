package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

import java.awt.event.KeyEvent;
import java.util.List;

// Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
// Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
// Isso sera' de evitar na versao a serio do projeto

public class Fireman extends Controllable implements Movable{

	public Fireman(Point2D position) {
		super(position);
		super.setOverFire(false);

	}
	
	// Metodos de ImageTile
	@Override
	public String getName() {
		return "fireman";
	}

	@Override
	public int getLayer() {
		return 4;
	}
	
	@Override
	public void changeTerrain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(int key) {
		if(super.move(key)) {
			setPosition(super.getNewPosition());
		} else {
			extinguishFire(super.getNewPosition(), super.getRandDir());
		}		
	}
	
	// Apaga o fogo 
	public void extinguishFire(Point2D p, Direction randDir) {
		
		Water water = null;
		ImageTile fire = Controller.getInstance().existentFire(p);
		ImageTile obj = Controller.getInstance().existentBurnable(p);
		if(fire != null) {
			if(randDir.equals(Direction.DOWN)) {
				water = new Water(p, "water_down");
				
			}
			if(randDir.equals(Direction.UP)) {
				water = new Water(p, "water_up");
				
			}
			if(randDir.equals(Direction.LEFT)) {
				water = new Water(p, "water_left");
							}
			if(randDir.equals(Direction.RIGHT)) {
				water = new Water(p, "water_right");
				
			}
			water.sendWater((Fire)fire);

			((Burnable) obj).putOut();
			
			Controller.getInstance().changeScore(5);
		}
	}
}
