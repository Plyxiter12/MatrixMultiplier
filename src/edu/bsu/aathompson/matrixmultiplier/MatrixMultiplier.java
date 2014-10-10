package edu.bsu.aathompson.matrixmultiplier;
import java.io.File;
import java.io.IOException;


public class MatrixMultiplier{
	
	private static int amountOfThreads;
	public static int threadCount = 0;

	public static int[][] multiply(int[][] m1, int[][] m2) throws InterruptedException {
		int m1rows = m1.length;
		int m2cols = m2[0].length;
		
		int[][] resultMatrix = new int[m1rows][m2cols];
		amountOfThreads = m2cols*m1rows;
		Thread[] thrdArray = new Thread[amountOfThreads];

		for (int i = 0; i < m1rows; i++) {
			for (int j = 0; j < m2cols; j++) {
				thrdArray[threadCount] = new Thread( new MatrixThread(resultMatrix, i, j));
				thrdArray[threadCount].start();
				thrdArray[threadCount].join();
				threadCount++;
			}
		}
		return MatrixThread.resultMatrix;
		
	}


	public static void main(String[] argv) throws InterruptedException, IOException {
		
		File outputPath = new File("data/OutputText.txt");
		MatrixReader.getDimensions();
		
		int[][] x = MatrixReader.readFirstMatrix(); 
		int[][]	y = MatrixReader.readSecondMatrix();
		int[][] z = MatrixMultiplier.multiply(x, y);
		
		MatrixWriter.printToConsole(x);
		MatrixWriter.printToConsole(y);
		MatrixWriter.printToConsole(z);
		
		MatrixWriter.printToFile(outputPath, x);
		MatrixWriter.printToFile(outputPath, y);
		MatrixWriter.printToFile(outputPath, z);
		
	}
}
