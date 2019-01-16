/*
 * Screw class extends Outer.
 * Abstract so that it cannot be instantiated.
 * Includes the attributes for head and drive which are unique to screws.
 */

import java.io.Serializable;

public abstract class Screw extends Outer implements Serializable{

	private static final long serialVersionUID = -4416101118194048198L;
	private String[] headList = {"Bugle", "Flat", "Oval", "Pan", "Round"};
	private String head;
	private String[] driveList = {"6-Lobe", "Philips", "Slotted", "Square"};
	private String drive;
	
	//8 parameter constructor accessing outer using super. 
	public Screw(double lth, String trd, String mat, String fin, String hd, String drv, double price, int units) throws IllegalFastener {
		super(lth, trd, mat, fin, price, units);
		setDrive(drv);
		setHead(hd);
	}

	//mutator for the head attribute.
	private void setHead(String hd) throws IllegalFastener{
		if(illegalEntry(hd, headList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		head = hd;
	}

	//mutator for the drive attribute.
	private void setDrive(String drv) throws IllegalFastener {
		if(illegalEntry(drv, driveList)) //ensures the input is in the accepted list of inputs.
			throw new IllegalFastener();
		drive = drv;
	}

	@Override
	public String toString() {
		return head + " head, " + drive + " drive, " + super.toString();
	}
}
