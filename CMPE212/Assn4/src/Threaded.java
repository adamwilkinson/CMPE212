/*
 * Threaded class extends from Fastener.
 * Abstract so that it cannot be instantiated.
 * Includes the attribute for thread and updates the list of finishes.
 */

import java.io.Serializable;

public abstract class Threaded extends Fastener implements Serializable{
	
	private static final long serialVersionUID = 7332633766611507613L;
	private String[] threadList = {"#8-13", "#8-15", "#8-32", "#10-13", "#10-24", "#10-32", "1/4-20", "5/16-18", "3/8-16", "7/16-14", "1/2-13", "5/8-11", "3/4-10"};
	private String thread;
	private String[] finishList = {"Chrome", "Plain", "Yellow Zinc", "Zinc", 
			"Hot Dipped Galvanized", "Black Phosphate", "ACQ 1000 Hour", "Lubricated"};
	
	//Five parameter constructor for threaded
	public Threaded(String trd, String mat, String fin, double price, int units) throws IllegalFastener {
		super(mat, fin, price, units);
		setThread(trd);
		if(illegalEntry(fin,finishList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
	}//end Threaded

	//mutator for thread
	private void setThread(String trd) throws IllegalFastener{
		if(illegalEntry(trd, threadList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		thread = trd;
	}//end setThread
	
	@Override
	public String toString() {
		return thread + " thread, " + super.toString();
	}
}
