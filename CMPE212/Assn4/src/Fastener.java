/*
 * Fastener Class is the first class in the hierarchy.
 * It acts as the most general classification class.
 * Abstract so it is not able to be instantiated.
 * 
 * Description: this class initializes the attributes required for the fasteners
 * it includes a constructor, mutators, some legality checks, and accessors.
 * 
 * Author: Adam Wilkinson
 * NetID: 15ajw2
 * Version 1.0
 */

import java.io.Serializable;

public abstract class Fastener implements Serializable{

	private static final long serialVersionUID = 1167878093479806017L;
	private String[] materialsList = {"Brass", "Stainless Steel", "Steel"};
	private String material;
	private String[] finishList = {"Chrome", "Plain", "Yellow Zinc", "Zinc", "Bright", 
				"Hot Dipped Galvanized", "Black Phosphate", "ACQ 1000 Hour", "Lubricated"};
	private String finish;
	private double unitPrice;
	private int numberOfUnits;
	
	//Four parameter constructor for Fastener
	public Fastener(String mat, String fin, double price, int units) throws IllegalFastener {
		if(!(mat instanceof String) || !(fin instanceof String)) //checks if the inputs are null.
			throw new IllegalFastener("Material and Finish cannot be null.");
		setFinishMaterial(fin, mat);
		setUnitPrice(price);
		setNumberOfUnits(units);		
	}
	
	//Checks if the entry is in the list of entries and returns false if it is.
	public boolean illegalEntry(String input, String[] list) {
		for(String str: list) {
			if(str.equals(input))
				return false;
		}//end for
		return true;
	}//end illegalEntry
	
	//Checks if the entry is in the list of entries and returns false if it is.
	public boolean illegalEntry(double input, double[] list) {
		for(double str: list) {
			if(str == input)
				return false;
		}//end for
		return true;
	}//end illegalEntry
		
	//mutator for the number of units
	private void setNumberOfUnits(int units) throws IllegalFastener{
		if(units < 1 || units > 10000 || (units != 1 && units % 5 != 0))//makes the necessary restriction checks
			throw new IllegalFastener();
		numberOfUnits = units;
	}//end setNumberOfUnits

	//mutator for the unit price
	private void setUnitPrice(double price) throws IllegalFastener {
		if(price < 0) //only restriction is that it must be greater than 0.
			throw new IllegalFastener();
		unitPrice = price;
	}//end setUnitPrice

	//mutator for the finish and material types
	private void setFinishMaterial(String fin, String mat) throws IllegalFastener {
		if((illegalEntry(fin, finishList) || illegalEntry(mat, materialsList)) || //checks if the inputs are in the respective lists
		((mat.equals(materialsList[0]) || mat.equals(materialsList[1])) && !(fin.equals(finishList[1]))) || //checks if either material or finish is an illegal entry
		((fin.equals(finishList[6]) || fin.equals(finishList[7]) || fin.equals(finishList[8])) && !(mat.equals(materialsList[2])))) //checks that the material is steel before allowing one of the steel screw finishes.
			throw new IllegalFastener();
		finish = fin;
		material = mat;
	}//end setFinish
	
	//abstract getOrderCost method
	public abstract double getOrderCost(int units);
	
	@Override
	public String toString() {
		return material + ", with a " + finish + " finish. " + numberOfUnits + " in a unit, " + unitPrice + " per unit.";
	}
	
}
