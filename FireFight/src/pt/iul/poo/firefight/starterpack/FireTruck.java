package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;
import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class FireTruck extends Controllable implements Movable{


	public FireTruck(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "firetruck";
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	@Override
	public void changeTerrain() {
		// TODO Auto-generated method stub
		
	}

	public void extinguishFire(Point2D p, Direction randDir) {
		
		Water water = null;
		
		List<Point2D> positionsToExtinguish = p.getFrontRect(randDir, 3, 2);
		
		for(Point2D pos : positionsToExtinguish) {
			ImageTile fire = Controller.getInstance().existentFire(pos);
			ImageTile obj = Controller.getInstance().existentBurnable(pos);
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
				water.sendWater((Fire)fire);;
	
				((Burnable) obj).putOut();
				
				Controller.getInstance().changeScore(10);
			}
		}
	}
	
	public void action(int key) {
		if(super.move(key)) {
			setPosition(super.getNewPosition());
		} else {
			extinguishFire(super.getNewPosition(), super.getRandDir());
		}	
	}
}
