package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Controllable extends GameElement{

	private boolean overFire;
	private Point2D newPosition = null;
	private Direction randDir = null;
	
	public Controllable(Point2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	
	public void setOverFire(boolean b) {
		this.overFire = b;
	}
	
	public Direction getRandDir() {
		return randDir;
	}

	public void setNewPosition(Point2D newPosition) {
		this.newPosition = newPosition;
	}

	public Point2D getNewPosition() {
		return newPosition;
	}

	public boolean canMoveTo(Point2D p, Direction randDir) {

		if (!ImageMatrixGUI.getInstance().isWithinBounds(p))
			return false; 
		
		if(overFire == false) {
			if(Controller.getInstance().existentFire(p) != null) {
					return false;
			}
		}
		return true;
	}
	
	public boolean move(int key) {	
	
		if(key == KeyEvent.VK_DOWN) {
			this.randDir = Direction.DOWN;
		}
		if(key == KeyEvent.VK_UP) {
			this.randDir = Direction.UP;
		}
		if(key == KeyEvent.VK_LEFT) {
			this.randDir = Direction.LEFT;
		}
		if(key == KeyEvent.VK_RIGHT) {
			this.randDir = Direction.RIGHT;
		}

		 setNewPosition(getPosition().plus(randDir.asVector()));
		 
		if (canMoveTo(newPosition, randDir)) {
			return true;
			
		} else {

			return false;
			
		}
	}
}
