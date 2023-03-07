package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Fire extends GameElement {

	private List<Point2D> spreadPositions = this.getPosition().getNeighbourhoodPoints();
		
	public Fire(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "fire";
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	public void spreadTo() {
				
		if(spreadPositions != null) {
			
			for(Point2D p : spreadPositions) {
				if(ImageMatrixGUI.getInstance().isWithinBounds(p) && Controller.getInstance().existentFire(p) == null 
						&& !Controller.getInstance().getFiremanPos().equals(p) 
						&& Controller.getInstance().getBulls() != null) {
					if(!Controller.getInstance().getBullPos().equals(p)) {
						ImageTile obj = Controller.getInstance().existentBurnable(p);
						if(obj instanceof Burnable) {
							 ((Burnable) obj).burn(p);
						}
					}
				}
			}
						
		}
		
	}
}
