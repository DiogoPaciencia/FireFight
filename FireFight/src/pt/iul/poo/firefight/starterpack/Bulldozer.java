package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Bulldozer extends Controllable implements Movable{

	public Bulldozer(Point2D position) {
		super(position);
		super.setOverFire(false);
	}
	
	@Override
	public String getName() {
		return "bulldozer";
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	@Override
	public void extinguishFire(Point2D p, Direction randDir) {
		// TODO Auto-generated method stub
		
	}

	public void changeTerrain() {
		
		ImageTile obj = Controller.getInstance().existentBurnable(getPosition());
		
		if(obj != null) {
			Controller.getInstance().removeObject(obj);
			ImageMatrixGUI.getInstance().removeImage(obj);
			Controller.getInstance().addObject(new Land(getPosition()));
		}
	}
	
	public void action(int key) {
		if(super.move(key)) {
			setPosition(super.getNewPosition());
			changeTerrain();			
		}
	}
}
