/*
 * Outer class extends Threaded
 * Abstract so that it cannot be instantiated.
 * Adds the length attribute to the outer class.
 */

import java.io.Serializable;

public abstract class Outer extends Threaded implements Serializable{

	private static final long serialVersionUID = -406819853919818583L;
	private double length;
	
	//6 parameter constructor accessing Threaded using super.
	public Outer(double lth, String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(trd, mat, fin, price, units);
		setLength(lth);
	}

	//mutator for the length attribute.
	private void setLength(double lth) throws IllegalFastener{
		if(illegalLength(lth)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		length = lth;
	}

	//A check to determine the length is not an invalid entry.
	private boolean illegalLength(double lth) {
		if((lth >= 0.5 && lth <= 6 && lth % 0.25 == 0.0) || //accepted conditions.
			(lth >= 6 && lth <= 11 && lth % 0.5 == 0.0) ||
			(lth >= 11 && lth <= 20 && lth % 1 == 0.0)) {
			return false;
		}
	return true;
	}
	
	@Override
	public String toString() {
		return length + "\" long, " + super.toString();
	}
}
