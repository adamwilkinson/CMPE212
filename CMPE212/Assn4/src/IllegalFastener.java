/*
 * Illegal fastener exception thrown in the case of an exception in the fasteners hierarchy.
 * 
 * Author: Adam Wilkinson
 * NetID: 15ajw2
 */
public class IllegalFastener extends Exception {

	private static final long serialVersionUID = 1L;

	//default method
	public IllegalFastener() { 
		super("Illegal parameter value entered.");
	}//end IllegalFastener
	
	//allows for a specific message.
	public IllegalFastener(String err) {
		super(err);
	}//end IllegalFastener
}
