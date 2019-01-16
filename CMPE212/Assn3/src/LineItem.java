import java.io.Serializable;
/** 
 * An object that describes a single line item from a Pizza order.
 * 
 * @author Adam Wilkinson
 * @version 1.0
 */
public class LineItem implements Comparable<LineItem>, Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numberOfPizzas;
	private Pizza pizzaChoice;
	
	/**
	 * Full parameter constructor.
	 * @param numPizzas The number of pizzas wanted in integer form.
	 * @param pizzaCh The type of pizza chosen in Pizza object form.
	 * @throws IllegalPizza if the arguments are not legal.
	 */
	public LineItem(int numPizzas, Pizza pizzaCh) throws IllegalPizza {
		setPizzaChoice(pizzaCh);
		setNumber(numPizzas);
	} //end LineItem
	
	/**
	 * Single parameter constructor, setting the number of pizza's to one by default.
	 * @param pizzaChoice The type of pizza chosen in Pizza object form.
	 * @throws IllegalPizza if the argument is invalid.
	 */
	public LineItem(Pizza pizzaChoice) throws IllegalPizza {
		setPizzaChoice(pizzaChoice);
		setNumber(1);
	} //end LineItem
	
	/**
	 * Sets the number of pizza's wanted.
	 * @param numPizzas the number of pizza's wanted
	 * @throws IllegalPizza if the number of pizza's is less than one,
	 * or greater than 100.
	 */
	public void setNumber(int numPizzas) throws IllegalPizza {
		if(numPizzas < 1 || numPizzas > 100)
			throw new IllegalPizza("Illegal entry: '" + numberOfPizzas + "' is not a valid number of pizzas.");
		numberOfPizzas = numPizzas;
	} //end setNumberOfPizzas
	
	//Called by the constructor to set the pizza type.
	private void setPizzaChoice(Pizza pizzaCh) throws IllegalPizza {
		if (pizzaCh == null)
			throw new IllegalPizza();
		pizzaChoice = pizzaCh;
	} //end setPizzaChoice
	
	/**
	 * Accessor to get the number of pizza's wanted.
	 * @return The number of pizza's wanted.
	 */
	public int getNumber() {
		return numberOfPizzas;
	} //end getNumberOfPizzas
	
	/**
	 * Accessor to get the type of pizza.
	 * @return The type of pizza.
	 */
	public Pizza getPizza() {
		return pizzaChoice.clone(); //not sure if this clone is necessary, but im being cautious
	} //end getPizzaChoice
	
	/**
	 * Accessor to get the total cost of the line item.
	 * @return The cost of the line item.
	 */
	public double getCost() {
		double cost = pizzaChoice.getCost()*numberOfPizzas;
		if(numberOfPizzas > 9 && numberOfPizzas <= 20)
			cost = cost*0.90; //10% discount
		else if(numberOfPizzas > 20)
			cost = cost*0.85; //15% discount
		return cost;
	} //end getCost
	
	/**
	 * A string representation of the line item.
	 * @return A String representation of all the attributes in LineItem, along
	 * with the total cost of the line.
	 */
	@Override //overrides the toString method in the Object class.
	public String toString() {
		String aString;
		if(numberOfPizzas < 10) //to ensure the lines match up.
			aString = " " + numberOfPizzas + " " + pizzaChoice.toString();
		else
			aString = numberOfPizzas + " " + pizzaChoice.toString();
		return aString;
	} //end toString
	
	/**
	 * Compares LineItem objects on the basis of total cost.
	 * @param other The other LineItem object being compared against.
	 * @return a negative <code>int</code> if the supplied object has a lower cost,
	 * zero if they are within 1.00 of each other, and positive if the current object
	 * has a lower cost.
	 */
	public int compareTo(LineItem other) { 
		return (int)(other.getCost() - getCost());
	} //end compareTo

}//end LineItem
