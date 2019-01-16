
	/*
	 * This program compares reading an array of random numbers in five different ways:
	 * as text, as binary numbers, as a binary object, and using a random access file 
	 * using both Java 6 and Java 7 code.
	 * 
	 * You must run DataFilingExperiment.java first to create the files.
	 * 
	 * Time of execution results are reported.
	 * 
	 * By Alan McLeod
	 */

	import java.io.IOException;
	import java.io.BufferedInputStream;
	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileInputStream;
	import java.io.ObjectInputStream;
	import java.io.RandomAccessFile;
	import java.nio.ByteBuffer;
	import java.nio.DoubleBuffer;
	import java.nio.channels.FileChannel;
	import java.nio.file.Files;
	import java.nio.file.LinkOption;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardOpenOption;

	public class total {

		final static int NUMNUMS = 10000;		// The size of the array

		// Reads an array of doubles from the provided filename as text
		public static double[] readText(String inputFile) {
			double[] array = new double[NUMNUMS];
			Path file = Paths.get(inputFile);
			String line;
			try (BufferedReader reader = Files.newBufferedReader(file)) {
				for (int i = 0; i < NUMNUMS; i++) {
					line = reader.readLine();
					array[i] = Double.parseDouble(line.trim()); 
				}
			} catch (IOException err) {
				System.err.println(err.getMessage());
			} catch (NumberFormatException err) {
				System.err.println(err.getMessage());			
			}
			return array;
		} // end readText

		// Reads an array of doubles to the provided filename as binary data
		// Uses ObjectInputStream
		public static double[] readBinaryFile(String inputFile) {
			double[] array = new double[NUMNUMS];
			try (ObjectInputStream binFileIn = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(inputFile)))) {
				for (int i = 0; i < NUMNUMS; i++)
					array[i] = binFileIn.readDouble();
			} catch (FileNotFoundException err) {
				System.out.println(err.getMessage());
			} catch (IOException err) {
				System.out.println(err.getMessage());
			} // end try catch
			return array;
		} // end readBinaryFile

		// Reads an array of doubles from the provided filename as a binary object
		// Used ObjectInputStream and reads entire array at once.
		public static double[] readObjectFile(String inputFile) {
			double[] array = new double[NUMNUMS];
			try (ObjectInputStream binFileIn = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(inputFile)))) {
				array = (double[])(binFileIn.readObject());
			} catch (ClassNotFoundException err) {
				System.out.println(err.getMessage());
			} catch (FileNotFoundException err) {
				System.out.println(err.getMessage());
			} catch (IOException err) {
				System.out.println(err.getMessage());
			} // end try catch
			return array;
		} // end readObjectFile

		// Reads an array of doubles from the provided filename using random file access
		// and Java 6 code.
		public static double[] readRandomFileJava6(String inputFile) {
			RandomAccessFile randFileIn = null;
			try {
				randFileIn = new RandomAccessFile(inputFile, "rw");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} // end try catch
			double[] array = new double[NUMNUMS];
			try {
				for (int i = 0; i < NUMNUMS; i++)
					array[i] = randFileIn.readDouble();
				randFileIn.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} // end try catch
			return array;
		} // end readRandomFileJava6

		// Reads an array of doubles from the provided filename using random file access
		// and Java 7 code.
		public static double[] readRandomFileJava7(String inputFile) {
			double[] array = new double[NUMNUMS];;
			ByteBuffer buff = ByteBuffer.allocate(8 * NUMNUMS);
			DoubleBuffer dBuff = buff.asDoubleBuffer();
			Path file = Paths.get(inputFile);
			if (Files.exists(file, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(file)) {
				StandardOpenOption readOpt = StandardOpenOption.READ;
				try (FileChannel fc = (FileChannel.open(file, readOpt))) {
					fc.position(0);
					fc.read(buff);
				} catch (IOException err) {
					System.out.println("I/O Exception: " + err);
				}
				dBuff.get(array);
			}
			return array;
		} // end readRandomFileJava7

		// Displays the results
		public static void displayResults(String filename, long runTimeNanoseconds) {
			System.out.printf("\n%-34s", filename);
			System.out.printf("%7.2f milliseconds.", runTimeNanoseconds * 1e-6);
		} // end displayResults

		public static void main(String[] args) {

			long startTime, runTime;	// Used to time the execution
			String filename;		// The name of the file

			// An array of random numbers between 0 and 1
			// The same array will be used for each technique.
			double[] testArray = new double[NUMNUMS];

			// Plain text
			filename = "TextFileBufferedWriter.txt";
			startTime = System.nanoTime();
			testArray = readText(filename);
			runTime = System.nanoTime() - startTime;
			displayResults(filename, runTime);

			// Binary format using ObjectOutputStream
			filename = "BinaryObjectOutputStream.dat";
			startTime = System.nanoTime();
			testArray = readBinaryFile(filename);
			runTime = System.nanoTime() - startTime;
			displayResults(filename, runTime);

			// A binary object
			filename = "BinaryObjectOutputStreamArray.dat";
			startTime = System.nanoTime();
			testArray = readObjectFile(filename);
			runTime = System.nanoTime() - startTime;
			displayResults(filename, runTime);

			// Using random file access, Java 6 code
			filename = "BinaryRandomAccessFileJava6.dat";
			startTime = System.nanoTime();
			testArray = readRandomFileJava6(filename);
			runTime = System.nanoTime() - startTime;
			displayResults(filename, runTime);

			// Using random file access, Java 7 code
			filename = "BinaryRandomAccessFileJava7.dat";
			startTime = System.nanoTime();
			testArray = readRandomFileJava7(filename);
			runTime = System.nanoTime() - startTime;
			displayResults(filename, runTime);

			System.out.println("\nAll done!");

		} // end main
		
	} // end DataReadingExperiment

