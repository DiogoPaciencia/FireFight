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

public class Plane  extends GameElement implements Bot{

	public Plane(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "plane";
	}

	@Override
	public int getLayer() {
		return 2;
	}	
	
	public void move() {
		
		for(int i=0; i<=1; i++) {
			setPosition(getPosition().plus(new Vector2D(0, -1)));
			Fire fire = Controller.getInstance().existentFire(getPosition());
			
			if(fire != null) {
				Controller.getInstance().removeObject(fire);
				ImageTile obj = Controller.getInstance().existentBurnable(getPosition());
				((Burnable) obj).putOut();
				ImageMatrixGUI.getInstance().removeImage(fire);	
				Controller.getInstance().changeScore(4);

			}
		}
	}
}

