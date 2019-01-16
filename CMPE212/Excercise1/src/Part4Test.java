// This version of the Part4 program demonstrates a very robust way of making sure
// that input is "legal".
import java.util.Scanner;
import java.util.InputMismatchException;

public class Part4Test {

	public static void main(String[] args) {
		int userNum = 0;
		Scanner screen = new Scanner(System.in);
		boolean inputOK = false;
		String dump = null;
		while (!inputOK) {
			System.out.print("Enter a number: ");
			try {
				userNum = screen.nextInt();
				dump = screen.nextLine();
				inputOK = true;
			} catch (InputMismatchException e) {
				dump = screen.nextLine();
				System.out.println("\"" + dump + "\" is not a legal integer, " +
						"please try again!");
			} // end try-catch block
		} // end input loop
		screen.close();
		userNum = userNum + 20;
		System.out.println("Your number plus 20 is " + userNum);
	} // end main method
	
} // end Part4 class