/*
 * Bolt Class extends Outer
 * Updates the list of finishes
 * Abstract so it is not able to be instantiated.
 */

import java.io.Serializable;

public abstract class Bolt extends Outer implements Serializable{
	
	private static final long serialVersionUID = -1406310192869784029L;
	private String[] finishList = {"Chrome", "Plain", "Yellow Zinc", "Zinc", "Hot Dipped Galvanized"};
	
	//6 parameter constructor accessing the outer class with super.
	public Bolt(double lth, String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(lth, trd, mat, fin, price, units);
		if(illegalEntry(fin,finishList))
			throw new IllegalFastener();
	}

}
