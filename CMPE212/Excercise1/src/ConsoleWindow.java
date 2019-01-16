import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleWindow {

	public static void main(String[] args) {
		int userNum = 0;
		String dump = null;
		boolean inputOK = false;
		Scanner screen = new Scanner(System.in);
		while(!inputOK) {
			try {
					System.out.print("Enter a number: ");
						userNum = screen.nextInt();
							dump = screen.nextLine();
								inputOK = true;
				}
			catch (InputMismatchException e) {
					dump = screen.nextLine();
					System.out.println("the input \"" + dump + "\" could not be converted to an int.");
				}
		}
		userNum = userNum + 20;
		System.out.println("Your number plus 20 is " + userNum);
		screen.close();
		
	} // end main method

} // end ConsoleWindow class