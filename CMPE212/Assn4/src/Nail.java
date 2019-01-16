/*
 * Nail class extends Fastener. initializes attributes specific to nails. 
 * abstract so that it cannot be instantiated.
 */
import java.io.Serializable;

public abstract class Nail extends Fastener implements Serializable{

	private static final long serialVersionUID = -3718288316826916306L;
	private double[] lengthList = {2, 2.5, 3, 3.25, 3.5, 6};
	private double length;
	private String[] sizeList = {"6D", "8D", "10D", "12D", "16D", "60D"};
	private String size;
	private double[] gaugeList = {2, 8, 9, 10.25, 11.5};
	private double gauge;
	
	//6 parameter constructor accessing Fastener using super.
	public Nail(String fin, double price, int units, double lth, String sz, double gge) throws IllegalFastener {
		super("Steel", fin, price, units);
		setLength(lth);
		setSize(sz);
		setGauge(gge);
	}

	//mutator for the gauge attribute,
	private void setGauge(double gge) throws IllegalFastener{
		if(illegalEntry(gge, gaugeList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		gauge = gge;
	}

	//mutator for the size attribute.
	private void setSize(String sz) throws IllegalFastener{
		if(illegalEntry(sz, sizeList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		size = sz;
	}

	//mutator for the length attribute.
	private void setLength(double lth) throws IllegalFastener{
		if(illegalEntry(lth, lengthList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		length = lth;
	}
	
	@Override
	public String toString() {
		return size + " size, " + length + "\" long, " + gauge + " gauge, " + super.toString();
	}
}
