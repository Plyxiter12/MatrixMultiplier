package edu.bsu.aathompson.matrixmultiplier;

public class MatrixThread implements Runnable{

	private int row;
	private int column;
	public static int[][] resultMatrix;
	
	MatrixThread(int[][] matrix, int row, int column){
		this.row = row;
		this.column = column;
		MatrixThread.resultMatrix = matrix;
	}
	
	@Override
	public void run() {
		
		int[][] matrix1 = MatrixReader.readFirstMatrix();
		int[][] matrix2 = MatrixReader.readSecondMatrix();
		
		for (int k = 0; k < MatrixReader.mColumns1; k++) {
			resultMatrix[row][column] += matrix1[row][k] * matrix2[k][column];
		}
	}
}
