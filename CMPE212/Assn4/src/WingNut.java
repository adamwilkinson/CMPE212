/*
 * WingNut class extends Nut.
 * Updates the accessor for cost and toString.
 */

import java.io.Serializable;

public class WingNut extends Nut implements Serializable{

	private static final long serialVersionUID = 1L;
	private double pricePerUnit;
	
	//5 parameter constructor accessing nut using super
	public WingNut(String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(trd, mat, fin, price, units);
		pricePerUnit = price;
	}

	@Override
	public double getOrderCost(int units) {
		double costOfOrder = units*pricePerUnit;
		return costOfOrder;
	}

	@Override
	public String toString() {
		return "Wing Nut, " + super.toString();
	}
	
	

}
