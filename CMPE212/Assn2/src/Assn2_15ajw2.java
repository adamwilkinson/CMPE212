/*Assignment 2
 * By: Adam Wilkinson
 * netID: 15ajw2
 * Due: Sunday Feb. 18th
 * Description: The purpose of this program is to read data from an excel file, analyze it and write
 * a summary of it to a new file. 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Assn2_15ajw2 {
	public static int DURATION = 1000;
	public static int NUMBER_OF_MOTORS = 7;
	
	//Main method has a loop to read the motor data for each motor.
	public static void main (String[] args) {
		for(int i = 1; i < NUMBER_OF_MOTORS + 1; i++) {
			double[] data = readMotorData("Logger.csv", i);
			String[][] outputArray = analyzeData(data);
			writeToFile(outputArray, i);
		}//end for loop
		System.out.println("All Done!");
	}//end main

	//Method to write the output array data to a csv file. It uses the path libraries to make a file and write to it.
	//It then loops through the out put array and transfers the data that way.
	public static void writeToFile (String[][] outputArray, int motorNumber) {

		Path file = Paths.get("Motor" + motorNumber + ".csv");
		try (BufferedWriter writer = Files.newBufferedWriter(file)) {
			for (int i = 0; i < outputArray.length; i++) { //outputArray.length = how many rows there are
				int j = 0; //keeps track of which column to write to.
				while (outputArray[i][j] != null && j < outputArray[i].length - 1) {
					if (i==0)
						writer.write(outputArray[i][j]);
					else
						writer.write(outputArray[i][j] + ",");
					if (outputArray[i][j].equals("Not used.")) {
						outputArray[i][j] = null;
						break;
					}
					j++;
				}//end while loop
				if (outputArray[i][j] != null)
					writer.write(outputArray[i][j]);
			writer.newLine();
			}//end for loop
		} catch (IOException err) {
			System.out.println("I/O Exception: " + err);
		}//end catch
	}//end writeToFile
	
	//Method to create the array based on number of pulses used to store the string variables
	//being exported to a file. The array is initialized with the headings and checks to see 
	//if a pulse has happened
	public static String[][] createOutputArray (int numberOfPulses) {
		String[][] outputArray = new String[numberOfPulses + 1][4];
		outputArray[0][0] = "start (sec)";
		outputArray[0][1] = ", finish (sec)";
		outputArray[0][2] = ", current (amps)";
		if (numberOfPulses == 0) //check if there were no pulses.
			outputArray[0][0] = "Not used.";
		return outputArray;
	}//end createOutputArray
	
	//Method to calculate the average current for each pulse. It then reduces the double to 3 significant
	//decimals places.
	public static double avgCalculator (Integer start, Integer finish, double[] motorData) {
		double sum = 0, average = 0;
		for (int i = start; i < finish; i++)
			sum += motorData[i];
		average = sum/(finish-start);
		average = 1000 * average; 
		average = Math.round(average); //reduce to 3 significant digits after the decimal.
		average = average/1000;		
		return average;
	}//end avgCalculator
	
	//Method to add the pulse information to the output array. 
	public static String[][] addToOutputArray (String[][] outputArray, int startSec, int finishSec, double averageAmps) {
		boolean empty = false;
		int i = 0; 
		while (!empty) {
			i++;
			if (outputArray[i][0] == null) 
				empty = true;
		}//end while
		outputArray[i][0] = Integer.toString(startSec); //convert the integer into string format.
		outputArray[i][1] = Integer.toString(finishSec); //convert the integer into string format.
		outputArray[i][2] = Double.toString(averageAmps); //convert the double into string format.
		if (averageAmps > 8) 
			outputArray[i][3] = " ***Current Exceeded***"; //output current exceeded if the average is above 8 amps.
		return outputArray;
	}//end addToOutputArray
	
	//Method to search the data and find the pulses. It keeps track of the time using the index values
	//of each element. Uses an ArrayList so that the length isnt specified.
	public static ArrayList pulseFinder (double[] motorData) {
		boolean startPulse = true;
		ArrayList startEndTimes = new ArrayList();
		for (int i = 0; i < DURATION; i++) { //iterate through the dataset.
			if (motorData[i] > 1 && startPulse == true) {
				startEndTimes.add(i); //adds the start time for the pulse to the list if startPulse is true and there is a pulse.
				startPulse = false;
			}//end if
			if (motorData[i] < 1 && startPulse == false) {
				startEndTimes.add(i); //adds the end time to the list if startPulse is false and the pulse is over.
				startPulse = true;
			}//end if
		}//end for loop
		return startEndTimes;
	}//end pulseCounter
	
	//Method to calculate the number of pulses based on the entries in the Array list.
	public static int pulseCounter (ArrayList startEndTimes) {
		int pulseCount = (startEndTimes.size() + 1)/2; //plus 1 because if a pulse starts in the time frame but doesn't end then this will consider it a pulse.
		return pulseCount;
	}//end pulseCounter
	
	//This method reads the a specific motor data from the file and puts it into an array of doubles. It
	//utilizes the path and paths libraries to read the data from the csv file.
	public static double[] readMotorData (String filename, int motorNumber) {
		double[] motorData = new double[DURATION];
		String nl = "";
		Path data = Paths.get(filename);
		try (BufferedReader read = Files.newBufferedReader(data)){ 
			for (int i = 0; i < DURATION; i++) {
				nl = read.readLine();
				String[] temp = nl.split(","); //to split the data into individual elements instead of a long string.
				motorData[i] = Double.parseDouble(temp[motorNumber]);
			}//end for loop
		} catch (IOException err){
			System.out.println(err);
		} catch (NumberFormatException err) {
			System.out.println(err);
		}//end catch statements
		return motorData;
	} //end readMotorData
	
	//Method that calls the other methods to find the pulses, count how many pulses there are, create the output
	//array, and fill the array with data.
	public static String[][] analyzeData (double[] motorData){
		int i = 0;
		ArrayList<Integer> startEndTimes = pulseFinder(motorData);
		int pulseCount = pulseCounter(startEndTimes);
		double[] average = new double[pulseCount];
		String[][] outputArray = createOutputArray(pulseCount);
		if (startEndTimes.size() == 0)
			return outputArray; 
		for(i = 0; i < pulseCount; i++) {
			average[i] = avgCalculator(startEndTimes.get(2*i), startEndTimes.get(2*i + 1), motorData);
			outputArray = addToOutputArray(outputArray, startEndTimes.get(2*i), startEndTimes.get(2*i + 1), average[i]);
		}//end for loop
		return outputArray;
	}//end analyzeData
	
}//end class
