/**
 * An Exception thrown by Pizza Object if there is an illegal entry.
 * <ul>
 * <li> The size must be either 1,2,3.
 * <li> The amount of cheese must be either 1,2,3.
 * <li> The amount of mushrooms must be either 0,1,2.
 * <li> The amount of pepperoni must be either 0,1,2.
 * <li> Cannot have mushrooms if there is no pepperoni.
 * </ul>
 * @author Adam Wilkinson
 * @version 1.0
 */
public class IllegalPizza extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default exception message.
	 */
	public IllegalPizza() {
		super("Illegal parameter value entered into Pizza object.");
	}
	
	/**
	 * Customizable exception message.
	 * @param err The message that will display upon exception being thrown.
	 */
	public IllegalPizza(String err) {
		super(err);
	}
}
