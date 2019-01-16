
public class Nim {

	public static int numberOfMarbles() {
		int marbles;
		marbles = (int) (10 + (Math.random() * 90));
		return marbles;
	}
	
	public static int firstPlay() {
		int playChoice;
		double i;
		i = Math.random();
		if (i<0.50) {
			playChoice = 0; //Computer starts
		}
		else {
			playChoice = 1; //I start
		}
	return playChoice;
	}
	
	public static int difficulty() {
		int diff;
		double i;
		i = Math.random();
		if (i<.50) {
			diff = 0; //computer plays random.
		}
		else {
			diff = 1; //computer plays smart.
		}
		return diff;
	}
	
	
	public static void dispStart(int marbles, int playChoice, int diff) {
		System.out.println("Starting with " + marbles + " marbles.");
		if (diff == 0)
			System.out.println("The computer is playing randomly.");
		else
			System.out.println("The computer is playing smart.");
		if (playChoice == 0)
			System.out.println("The computer plays first.");
		else
			System.out.println("You play first.");
	}
	
	public static int dispRemain(int marbles, int input) {
		int remain;
		remain = marbles - input;
		if (remain == 1)
			System.out.println("There is one marble left.");
		else
		System.out.println("There are " + remain + " marbles left.");
		return remain;
	}
	
	public static void dispInput(int input,int turn) {
		if (turn == 0) //computer turn	
			if (input == 1)
				System.out.println("The computer chose one marble.\n");
			else
			System.out.println("The computer chose " + input + " marbles.\n");
		else
			if(input == 1)
				System.out.println("You chose one marble.\n");
			else
			System.out.println("You chose " + input + " marbles.\n");
	}
	
	public static int getInput(int remain) {
		int low = 1;
		int input;
		int high = (int)remain/2;
		if (high == low)
			input = IOHelper.getInt(low, "Choose one marble: ",high);
		else
			input = IOHelper.getInt(low, "Choose between " + low + " and " + high + " marbles:", high);
		return input;
	}
	
	public static int compInput(int remain, int diff) {
		int choice = 0;
		if (diff == 1) { //plays smart
			while(remain - choice != 2 && remain - choice != 3 && remain - choice != 7 && remain - choice != 15 && remain - choice != 31 && remain - choice != 63 ) {
				choice++;
				if (choice > remain/2 ) {
					choice = (int) ((Math.random() + 0.01)*(remain/2)); //have to exclude 0.
						if (choice == 0)
							choice = 1;
					return choice; 
				}	
			}
		}
		else { //plays random
			choice = (int) ((Math.random() + 0.01)*(remain/2 - 1) + 1); //have to exclude 0.
		}
		if (choice == 0)
			choice = 1;
		return choice;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int marblesM = numberOfMarbles();
		int playChoiceM = firstPlay();
		int difficultyM = difficulty();
		dispStart(marblesM,playChoiceM,difficultyM);
		while(marblesM > 1) {
			if(playChoiceM == 0) {
				int compChoice = compInput(marblesM,difficultyM);
				dispInput(compChoice,playChoiceM);
				marblesM = dispRemain(marblesM,compChoice);
				playChoiceM = 1;
			}
			else {
				int userChoice = getInput(marblesM);
				dispInput(userChoice,playChoiceM);
				marblesM = dispRemain(marblesM,userChoice);
				playChoiceM = 0;
			}
		}
	if (playChoiceM == 0)
		System.out.println("\nYou win !!");
	else
		System.out.println("\nThe computer wins!!");
	}

}
