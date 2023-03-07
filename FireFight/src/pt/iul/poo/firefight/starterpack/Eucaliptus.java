package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Eucaliptus extends Burnable {

	public Eucaliptus(Point2D position) {
		super(position);
		super.setBurnProb(0.10);
		super.setPlaysUntilBurnt(5);
		super.setBurning(false);
		super.setBurntName("burnteucaliptus");
	}
	
	@Override
	public String getName() {
		return "eucaliptus";
	}


	@Override
	public int getLayer() {
		return 1;
	}
}
