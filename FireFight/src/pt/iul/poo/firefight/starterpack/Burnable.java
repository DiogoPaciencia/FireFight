package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Burnable extends GameElement{

	private double burnProb;
	private int playsUntilBurnt;
	private boolean isBurning;
	private String burntName;
	
	public Burnable(Point2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	
	public double getBurnProb() {
		return burnProb;
	}

	public void setBurntName(String burntName) {
		this.burntName = burntName;
	}

	public void setBurnProb(double burnProb) {
		this.burnProb = burnProb;
	}
	
	public int getPlaysUntilBurnt() {
		return playsUntilBurnt;
	}
	
	public void setPlaysUntilBurnt(int playsUntilBurnt) {
		this.playsUntilBurnt = playsUntilBurnt;
	}
	
	public boolean getBurning() {
		return this.isBurning;
	} 
	
	public void setBurning(boolean isBurning) {
		this.isBurning = isBurning;
	}

	public void putOut() {
		// TODO Auto-generated method stub
		isBurning = false;
	}
	
	public void setFire() {
		// TODO Auto-generated method stub
		isBurning = true;
		
	}

	public void burn(Point2D p) {
		// TODO Auto-generated method stub
		if(Math.random()<= burnProb ) {
			Controller.getInstance().addObject(new Fire(p));
			isBurning = true;
		}
	}
	
	public void burnt() {
		
		if(playsUntilBurnt == 0) {
			Controller.getInstance().removeObject(this);
			Controller.getInstance().removeObject(Controller.getInstance().existentFire(getPosition()));
			ImageMatrixGUI.getInstance().removeImage(this);
			Controller.getInstance().addObject(new Burnt (getPosition(), this.burntName));
			Controller.getInstance().changeScore(-2);
			
		}else {
			playsUntilBurnt--;
		}
		
	}
	
	
}
