package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public interface Movable {

	public void changeTerrain();
	
	public void action(int key);
	
	public void extinguishFire(Point2D p, Direction randDir);

}
