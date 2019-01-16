/*
 * Nut class extends InnerThreaded.
 * Abstract so that it cannot be instantiated.
 */
import java.io.Serializable;

public abstract class Nut extends InnerThreaded implements Serializable{

	private static final long serialVersionUID = -5478758919742613153L;

	//5 parameter constructor accessing InnerThreaded using super.
	public Nut(String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(trd, mat, fin, price, units);
	}

}
