package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Abies extends Burnable{
	
	public Abies(Point2D position) {
		super(position);
		super.setBurnProb(0.05);
		super.setPlaysUntilBurnt(20);
		super.setBurning(false);
		super.setBurntName("burntabies");
	}
	
	@Override
	public String getName() {
		return "abies";
	}


	@Override
	public int getLayer() {
		return 1;
	}
}
