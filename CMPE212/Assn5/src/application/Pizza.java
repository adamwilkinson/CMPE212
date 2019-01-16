package application;

import java.io.Serializable;
/**
 * This class is part of the solution for assignment 3. The class represents a pizza, containing
 * the size, the amount of cheese, mushrooms and pepperoni. It can also produce the cost of that pizza
 * using hard-coded prices.
 * @author Alan McLeod
 * @version 1.2
 */
public class Pizza implements Serializable {

	private static final long serialVersionUID = 5735694084835042047L;
	private String size;
	private String cheese;
	private String mushrooms;
	private String pepperoni;
	
	private float SMALL_COST = 7.00F;	// Includes one cheese
	private float MEDIUM_COST = 9.00F;
	private float LARGE_COST = 11.00F;
	private float COST_PER_TOPPING = 1.50F;
	
	private void setSize(String size) throws IllegalPizza {
		if (size == null)
			throw new IllegalPizza("Size not provided!");
		size = size.toLowerCase();
		if (size.equals("small") || size.equals("medium") || size.equals("large"))
			this.size = size;
		else
			throw new IllegalPizza("Illegal size!");		
	} // end setSize
	
	private void setCheese(String cheese) throws IllegalPizza {
		if (cheese == null)
			throw new IllegalPizza("Cheese not provided!");
		cheese = cheese.toLowerCase();
		if (cheese.equals("single") || cheese.equals("double") || cheese.equals("triple"))
			this.cheese = cheese;
		else
			throw new IllegalPizza("Illegal cheese!");		
	} // end setCheese
	
	private void setMushrooms(String mushrooms) throws IllegalPizza {
		if (mushrooms == null)
			throw new IllegalPizza("Mushrooms not provided!");
		mushrooms = mushrooms.toLowerCase();
		if (mushrooms.equals("none") || mushrooms.equals("single") || mushrooms.equals("double"))
			this.mushrooms = mushrooms;
		else
			throw new IllegalPizza("Illegal mushrooms!");		
	} // end setMushrooms
	
	private void setPepperoni(String pepperoni) throws IllegalPizza {
		if (pepperoni == null)
			throw new IllegalPizza("Pepperoni not provided!");
		pepperoni = pepperoni.toLowerCase();
		if (pepperoni.equals("none") || pepperoni.equals("single") || pepperoni.equals("double"))
			this.pepperoni = pepperoni;
		else
			throw new IllegalPizza("Illegal pepperoni!");		
	} // end setPepperoni
	
	/**
	 * The Pizza constructor.
	 * @param size Must be "small", "medium" or "large".
	 * @param cheese Must be "single", "double" or "triple".
	 * @param mushrooms Must be "none", "single" or "double".
	 * @param pepperoni Must be "none", "single" or "double".
	 * @throws IllegalPizza If any of the parameters are not legal or if pepperoni is "none" and
	 * mushrooms is not "none".
	 */
	public Pizza(String size, String cheese, String mushrooms, String pepperoni) throws IllegalPizza {
		setSize(size);
		setCheese(cheese);
		setMushrooms(mushrooms);
		setPepperoni(pepperoni);
		if (this.pepperoni.equals("none") && !this.mushrooms.equals("none"))
			throw new IllegalPizza("You must have pepperoni with your mushrooms!");
	} // end full parameter constructor
	
	/**
	 * The default Pizza constructor.
	 * @throws IllegalPizza Should not be thrown.
	 */
	public Pizza() throws IllegalPizza {
		this("small", "single", "none", "single");
	} // end default constructor
	
	private float getSizeCost(String size) {
		switch (size) {
		case "small":
			return SMALL_COST;
		case "medium":
			return MEDIUM_COST;
		}
		return LARGE_COST;
	} // end getSizeCost
	
	private int translateTopping(String topping) {
		switch (topping) {
		case "none":
			return 0;
		case "single":
			return 1;
		case "double":
			return 2;
		}
		return 3;
	} // end translateTopping
	
	/**
	 * Returns the cost of the current Pizza object.
	 * @return The cost in dollars.  No tax.
	 */
	public float getCost() {
		float cost = getSizeCost(size);
		cost += ((translateTopping(cheese) - 1) + translateTopping(mushrooms) + 
				  translateTopping(pepperoni)) * COST_PER_TOPPING;
		return cost;
	} // end getCost
	
	private String checkNone(String topping) {
		if (topping.equals("none"))
			return "no";
		return topping;
	}
	
	/**
	 * Returns a string representation of the current object.
	 * @return A string description of the current Pizza object.
	 */
	public String toString() {
		String out = size + " pizza, " + cheese + " cheese, ";
		out += checkNone(mushrooms) + " mushrooms, ";
		out += checkNone(pepperoni) + " pepperoni.";
		out += String.format(" Cost: $%.2f each.", getCost());
		return out;
	} // end toString
	
	/**
	 * Tests to see if the current object is equal to the supplied Pizza object. Equality is
	 * defined as all attributes being exactly equal.
	 * @param other The supplied object for comparison.
	 * @return false if the objects are not equal or the supplied object is not a Pizza, true
	 * otherwise.
	 */
	public boolean equals(Object other) {
		if (other instanceof Pizza) {
			Pizza otherP = (Pizza)other;
			return size.equals(otherP.size) && cheese.equals(otherP.cheese) && 
				   mushrooms.equals(otherP.mushrooms) && pepperoni.equals(otherP.pepperoni);
		}
		return false;
	} // end equals
	
	/**
	 * Creates and returns a clone of the current Pizza object.
	 * @return A deep copy or clone of the current object. 
	 */
	public Pizza clone() {
		Pizza outP = null;
		try {
			outP = new Pizza(size, cheese, mushrooms, pepperoni);
		} catch (IllegalPizza e) {
		}
		return outP;
	} // end clone
	
} // end Pizza class
