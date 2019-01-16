import java.util.Scanner;

public class Palindromes {

	public static String massage(String aString) {
		String massagedString = new String();
		aString = aString.toLowerCase();
		for(int i=0; i< aString.length(); i++) {
			if (Character.isLetter(aString.charAt(i)))
				massagedString = massagedString + aString.charAt(i);
		} //end for
		return massagedString;
	}//end massage
	
	public static Boolean match(String aString) {
		if (aString.length() < 2 || aString.charAt(0) == aString.charAt(aString.length() - 1))
			return true;
		return false;
	}
	
	public static String strip(String aString) {
		String strippedString = new String();
		for(int i = 1; i < aString.length() - 1; i ++)
			strippedString = strippedString + aString.charAt(i);	
		return strippedString;
	}
	
	public static Boolean test(String aString) {
		aString = massage(aString);
		while(match(aString) && aString.length() > 1) {
			aString = strip(aString);
		}
		if(aString.length() < 2)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner screenInput = new Scanner(System.in);
		System.out.print("Rules:\n- Input a string of characters to check if it is a palindrome.\n- Case, numbers and spaces will be ignored.");
		while(true) {
			System.out.print("\nEnter String:");
			String userText = screenInput.nextLine();
			if(userText.length() < 2 || test(userText))
				System.out.print("Yes! '" + userText + "' is a palindrome.");
			else 
				System.out.print("Sorry, '" + userText + "' is not a palindrome.");
		}
	}//end main
}//end class Palindromes

