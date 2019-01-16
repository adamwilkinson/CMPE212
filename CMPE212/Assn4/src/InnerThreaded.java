import java.io.Serializable;

public abstract class InnerThreaded extends Threaded implements Serializable{

	private static final long serialVersionUID = -8987853647475963356L;

	//5 parameter constructor accessing Threaded using super
	public InnerThreaded(String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(trd, mat, fin, price, units);
	}

}
