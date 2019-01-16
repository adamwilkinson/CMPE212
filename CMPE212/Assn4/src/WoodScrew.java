/*
 * WoodScrew class extends Screw
 * Updates the accessor for cost and toString.
 * adds the attribute for point which is unique to wood screws.
 */

import java.io.Serializable;

public class WoodScrew extends Screw implements Serializable{

	private static final long serialVersionUID = 6103924932815505449L;
	private String[] pointList = {"Double Cut", "Sharp", "Type 17"};
	private String point;
	private double pricePerUnit;
	
	//9 parameter constructor accessing screw using super.
	public WoodScrew(double lth, String trd, String mat, String fin, String hd, String drv, String pt, double price, int units)
			throws IllegalFastener {
		super(lth, trd, mat, fin, hd, drv, price, units);
		setPoint(pt);
		pricePerUnit = price;
	}

	//mutator to set the attribute point.
	private void setPoint(String pt) throws IllegalFastener {
		if(illegalEntry(pt, pointList))
			throw new IllegalFastener();
		point = pt;
	}

	@Override
	public String toString() {
		return "Wood Screw, " + point + " point, " + super.toString();
	}
	
	@Override
	public double getOrderCost(int units) {
		double costOfOrder = units*pricePerUnit;
		return costOfOrder;
	}

}
