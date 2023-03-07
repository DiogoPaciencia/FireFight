package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class GameElement implements ImageTile{
	private Point2D position;

	
	public GameElement(Point2D pos){
		this.position=pos;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public static GameElement createObject(String type, Point2D pos){
		switch(type){
			case "a" : return new Abies(pos);
			case "p" : return new Pine(pos);
			case "e" : return new Eucaliptus(pos);
			case "m" :return new Grass(pos);
			case "_" :return new Land(pos);
			case "b" :return  new FuelBarrel(pos);
			case "Fireman" : return new Fireman(pos);
			case "Bulldozer" : return new Bulldozer(pos);
			case "Fire" :return  new Fire(pos);
			case "FiremanBot" :return  new FiremanBot(pos);
			case "FireTruck" :return  new FireTruck(pos);			
			
			default :return new Land(pos);
		}
	}
	
}
