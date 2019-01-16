/*
 * Carriage Bolt Class extends Bolt
 * Updates the getOrderCost method
 */

import java.io.Serializable;

public class CarriageBolt extends Bolt implements Serializable{

	private static final long serialVersionUID = -441811824775414635L;
	private double pricePerUnit;
	
	//6 parameter constructor accessing the bolt class with super
	public CarriageBolt(double lth, String trd, String mat, String fin, double price, int units)
			throws IllegalFastener {
		super(lth, trd, mat, fin, price, units);
		pricePerUnit = price;
	}

	@Override
	public String toString() {
		return "Carriage Bolt, " + super.toString();
	}
	
	@Override
	public double getOrderCost(int units) {
		double costOfOrder = units*pricePerUnit;
		return costOfOrder;
	}

}
