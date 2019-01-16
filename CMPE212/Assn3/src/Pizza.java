import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * A class to describe a single Pizza.
 * <p>
 * The size, amount of cheese, mushrooms and pepperoni is recorded. ADD MORE
 * <p>
 * This version demonstrates the implementation of Comparable and Serializable (for sorting and filing, respectively).
 * The mutators for pepperoni and mushrooms have been combined so both attributes have to be set at the same time.
 * This makes sure they cannot be set to an illegal value, because they depend on each other.
 * @author Adam Wilkinson
 * @version 1.0
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = 1L;
	private int size;
	private int cheese;
	private int mushrooms;
	private int pepperoni;
	private String[] sizes = {"small", "medium", "large"};
	private String[] numToppings = {"none", "single", "double", "triple"};
	private String[] numToppings2 = {"no", "single", "double", "triple"};
	private static DecimalFormat df = new DecimalFormat("#.00");
	
	/**
	 * Full parameter constructor.
	 * @param sz The desired size of the pizza (1=small, 2=medium, 3=large).
	 * @param chz The amount of cheese on the pizza in String form ("single", "double", "triple").
	 * @param mush The amount of mushrooms or lack there of ("none", "single", "double").
	 * @param pep The amount of pepperoni or lack there of ("none", "single", "double").
	 * @throws IllegalPizza if arguments are not legal.
	 */
	public Pizza(String sz, String chz, String mush, String pep) throws IllegalPizza {
		//checks if the inputs are of type null.
		if(!(sz instanceof String) || !(chz instanceof String) || 
			!(mush instanceof String) || !(pep instanceof String))
				throw new IllegalPizza("input paramater cannot be null");
		//calls the mutators to initialize the attributes.
		setSize(sz);
		setCheese(chz);
		setMushPep(mush, pep);
	}
	
	/**
	 * Default constructor creates a small, single cheese, single pepperoni pizza.
	 */
	public Pizza() {
		size = 1;
		cheese = 1;
		pepperoni = 1;
		mushrooms = 0;
	}

	
	//Sets the amount of mushrooms and the amount of pepperoni on the Pizza.
	private void setMushPep(String mush, String pep) throws IllegalPizza {
		int mushN = Arrays.asList(numToppings).indexOf(mush.toLowerCase()); //converts the input Strings into integer for simplicity.
		int pepN = Arrays.asList(numToppings).indexOf(pep.toLowerCase()); 
		if(mushN != 0 && pepN == 0 || mushN < 0 || mushN > 2 || pepN < 0 || pepN > 2) //checks if the input wants mushrooms without pepperoni.
			throw new IllegalPizza("Cannot order Mushrooms without Pepperoni.");
		mushrooms = mushN;
		pepperoni = pepN;
	} //end setMushPep


	//Sets the amount of cheese on the Pizza.
	private void setCheese(String chz) throws IllegalPizza {
		int chzN = Arrays.asList(numToppings).indexOf(chz.toLowerCase()); //converts the input String into integer for simplicity.
		if(chzN < 1 || chzN > 3)
			throw new IllegalPizza("Illegal Entry: " + chzN);
		cheese = chzN;
	} //end setCheese

	//Sets the size of the Pizza.
	private void setSize(String sz) throws IllegalPizza {
		int szN = Arrays.asList(sizes).indexOf(sz.toLowerCase()) + 1; //converts the input String into integer for simplicity.
		if(szN < 1 || szN > 3)
			throw new IllegalPizza("Illegal Entry: " + szN);
		size = szN;
	} //end setSize
	
	/**
	 * An accessor that returns the cost of the pizza. 
	 * @return The cost of the pizza.
	 */
	public double getCost () {
		double cost = 0; 
		if(size == 1)
			cost = 7.00 + (1.50*((cheese-1) + pepperoni + mushrooms));
		else if(size == 2)
			cost = 9.00 + (1.50*((cheese-1) + pepperoni + mushrooms));
		else 
			cost = 11.00 + (1.50*((cheese-1) + pepperoni + mushrooms));
		return cost;
	} //end getCost
	
	/**
	 * A String representation of the current object.
	 * @return A String containing the values of all the attributes.
	 */
	@Override
	public String toString() {
		double cost = getCost();
		String aString = sizes[size - 1] + " pizza, " + numToppings2[cheese] + " cheese, " + numToppings2[mushrooms];
		aString += " mushrooms, " + numToppings2[pepperoni] + " pepperoni. Cost: $" + df.format(cost) + " each.";
		return aString;
	} //end toString
	
	/**
	 * Tests two Pizza objects for equality.
	 * @return <code>true</code> if all the attributes of both objects are exactly equal, 
	 * <code>false</code> otherwise.
	 * @param compareObject The Pizza object being compared to.
	 */
	@Override //overrides the equals method from the Object class.
	public boolean equals(Object compareObject) {
		if(compareObject instanceof Pizza) {
			Pizza otherPizza = (Pizza) compareObject;
			if(otherPizza.size == size && 
				otherPizza.cheese == cheese && 
				otherPizza.mushrooms == mushrooms && 
				otherPizza.pepperoni == pepperoni) //checks if all attributes are equal.
				return true;
		}//end if
		return false;
	} //end equals
	
	/**
	 * Returns a copy of the current Pizza object.
	 * @return A copy of the current Pizza object.
	 */
	@Override
	public Pizza clone() {
		Pizza pizzaCopy = null;
		try {
			pizzaCopy = new Pizza(sizes[size - 1], numToppings[cheese], numToppings[mushrooms], numToppings[pepperoni]);
		} catch (IllegalPizza e) { // wont get here because we know the params are legal.
			return null;
		} //end try/catch
		return pizzaCopy;
	} //end clone
} //end Pizza
