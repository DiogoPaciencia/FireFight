package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Land extends GameElement {

	public Land(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return "land";
	}


	@Override
	public int getLayer() {
		return 0;
	}
}
