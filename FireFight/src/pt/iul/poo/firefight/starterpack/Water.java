package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que est� definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Water implements ImageTile {

	private Point2D position;
	private String name;

	public Water(Point2D position, String name) {
		this.position = position;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	public void sendWater(Fire fire) {
			
		Controller.getInstance().removeObject(fire);
		ImageMatrixGUI.getInstance().removeImage(fire);
		Controller.getInstance().addObject(this);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller.getInstance().removeObject(this);
		ImageMatrixGUI.getInstance().removeImage(this);

		}
	}
