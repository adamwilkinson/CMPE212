import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Exercise4prt4 {
	
	public static double[] randomArray (int size) {
		double[] array = new double[size];
		for(int i = 0; i < size; i++)
			array[i] = Math.random();
		return array;
	} //end randomArray
	
	public static long fileSize (String filename) {
		Path file = Paths.get(filename);
		long fileSize = 0;
		try {
				fileSize = Files.size(file);
			} catch (IOException e) {
				System.out.println("IOException: " + e);
			}
		return fileSize;
	}//end fileSize
	
	// Displays the results
    public static void displayResults(String filename, long runTimeNanoseconds) {
        System.out.printf("\n%-34s", filename);
        System.out.printf("%8d bytes, saved in:", fileSize(filename.trim()));
        System.out.printf("%7.2f milliseconds.\n", runTimeNanoseconds * 1e-6);
    } // end displayResults
    
    public static void writeText(String outputFile, double[] array) {
    		Path file = Paths.get(outputFile);
    		try (BufferedWriter writer = Files.newBufferedWriter(file)) {
    			for (double aVal : array)
    					writer.write(aVal + "\r\n"); 
    		} catch (IOException err) {
    			System.err.println(err.getMessage());
    		}
    }
    
    public static void writeBinaryFile (String outputFile, double[] array) {
    		Path file = Paths.get(outputFile);
    		try (ObjectOutputStream binFileOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.toFile())))) {
    				for(double e : array)
    					binFileOut.writeDouble(e);
    		} catch (FileNotFoundException err) {
    			System.out.println(err.getMessage());
    		} catch (IOException err) {
    			System.out.println(err.getMessage());
    		}
    }
    
    public static void randomAccessBinary (String outputFile, double[] array) {
    		Path file = Paths.get(outputFile);
    		ByteBuffer buff = ByteBuffer.allocate(8 * array.length);
    		DoubleBuffer dBuff = buff.asDoubleBuffer();
    		for (double val : array)
    			dBuff.put(val);
    		long bytesWritten;
    		StandardOpenOption writeOpt = StandardOpenOption.WRITE;
    		StandardOpenOption createOpt = StandardOpenOption.CREATE;
    		try (FileChannel fc = FileChannel.open(file, createOpt, writeOpt)) {
    			fc.position(0);
    			bytesWritten = fc.write(buff);
    		} catch (IOException err) {
    				System.out.println("I/O Exception: " + err);
    		}
    }
    
    
    public static void main(String[] args) {

        int numNums = 10000;		// The size of the array
        long startTime, runTime;	// Used to time the execution
        String filename;			// The name of the file

        // An array of random numbers between 0 and 1
        // The same array will be used for each technique.
        double[] testArray = randomArray(numNums);

        // Plain text
        filename = "TextFileBufferedWriter.txt";
        startTime = System.nanoTime();
        writeText(filename, testArray);
        runTime = System.nanoTime() - startTime;
        displayResults(filename, runTime);

        // Binary format using ObjectOutputStream
        filename = "BinaryObjectOutputStream.dat";
        startTime = System.nanoTime();
        writeBinaryFile(filename, testArray);
        runTime = System.nanoTime() - startTime;
        displayResults(filename, runTime);

        // Using random file access, Java 7
        filename = "BinaryRandomAccessFileJava7.dat";
        startTime = System.nanoTime();
        randomAccessBinary(filename, testArray);
        runTime = System.nanoTime() - startTime;
        displayResults(filename, runTime);

        System.out.println("\nAll done!");

    } // end main
    

}
