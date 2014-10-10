package edu.bsu.aathompson.matrixmultiplier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatrixWriter {
	
	private static int cMatrixCounter;
	private static int fMatrixCounter;
	private static Date date = new Date( );
    private static SimpleDateFormat ft = new SimpleDateFormat ("E MM/dd/yyyy 'at' hh:mm:ss a zzz");
	
	public static void printToConsole(int[][] m) throws IOException {
		int rows = m.length;
		int columns = m[0].length;
		
		if(fMatrixCounter == 2){
			System.out.println();
			System.out.print("Result:");
		}
		System.out.println();
		System.out.println("dimensions[" + rows + "," + columns + "] =");
		for (int i = 0; i < rows; i++) {
			System.out.print("{");
			for (int j = 0; j < columns; j++) {
				System.out.print(" " + m[i][j] + " ");
			}
			System.out.println("}");
		}
		
		
		if(fMatrixCounter == 2){
			System.out.println();
			System.out.println("There were " + MatrixMultiplier.threadCount + " concurrent threads!");
		}
		fMatrixCounter++;
	}

	public static void printToFile(File file, int[][] m) throws IOException {

		FileWriter outputFile = new FileWriter(file, true);
		int rows = m.length;
		int columns = m[0].length;
		
		if(cMatrixCounter == 2){
			outputFile.write(System.lineSeparator());
			outputFile.write("Result:");
		}
		outputFile.write(System.lineSeparator());
		outputFile.write("dimensions[" + rows + "," + columns + "] =");
		outputFile.write(System.lineSeparator());
		for (int i = 0; i < rows; i++) {
			outputFile.write("{");
			for (int j = 0; j < columns; j++) {
				outputFile.write(" " + m[i][j] + " ");
			}
			outputFile.write("}");
			outputFile.write(System.lineSeparator());
		}
		

		if(cMatrixCounter == 2){
			outputFile.write(System.lineSeparator());
			outputFile.write("There were " + MatrixMultiplier.threadCount + " concurrent threads!");
			outputFile.write(System.lineSeparator());
			outputFile.write("Calculated on: " + ft.format(date));
			outputFile.write(System.lineSeparator());
			outputFile.write("--------------------------------");
		}
		outputFile.close();
		cMatrixCounter++;
	}
}
