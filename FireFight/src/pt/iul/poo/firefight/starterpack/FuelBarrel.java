package pt.iul.poo.firefight.starterpack;

import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class FuelBarrel extends Burnable {
	
	public FuelBarrel(Point2D position) {
		super(position);
		super.setBurnProb(0.90);
		super.setPlaysUntilBurnt(3);
		super.setBurning(false);
		super.setBurntName("explosion");
	}
	
	@Override
	public String getName() {
		return "fuelbarrel";
	}


	@Override
	public int getLayer() {
		return 1;
	}
	
	public void burnt() {
		if(super.getPlaysUntilBurnt() == 0) {
			Burnt explosion = new Burnt (getPosition(), "explosion");
			Land land = new Land(getPosition());
			ImageMatrixGUI.getInstance().removeImage(Controller.getInstance().existentFire(getPosition()));
			Controller.getInstance().removeObject(Controller.getInstance().existentFire(getPosition()));
			Controller.getInstance().addObject(explosion);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Controller.getInstance().removeObject(explosion);
			ImageMatrixGUI.getInstance().removeImage(explosion);
			Controller.getInstance().removeObject(this);
			Controller.getInstance().addObject(land);
			Controller.getInstance().changeScore(-10);
			burnNeighbours();
		}else {
			super.setPlaysUntilBurnt(super.getPlaysUntilBurnt()-1);
		}
	}
	
	public void burnNeighbours() {
		
		List<Point2D> neighbours = getPosition().getWideNeighbourhoodPoints();
		
		for(Point2D pos : neighbours) {
			ImageTile obj = Controller.getInstance().existentBurnable(pos);
			if(obj instanceof Burnable && Controller.getInstance().existentFire(pos) == null) {
				((Burnable) obj).setBurnProb(1);
				((Burnable) obj).burn(pos);
			}
		}
	}
}
