package edu.bsu.aathompson.matrixmultiplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class MatrixReader {
	private static LinkedList<Integer> matrixList1 = new LinkedList<Integer>();
	private static LinkedList<Integer> matrixList2 = new LinkedList<Integer>();
	private static File fileName = new File("data/InputMatrix2.txt");

	public static int mColumns1 = 0;
	public static int mRows1 = 0;
	public static int mColumns2 = 0;
	public static int mRows2 = 0;

	public static void getDimensions() {
		int rowCounter1 = 0;
		int rowCounter2 = 0;
		String[] columns1;
		String[] columns2;

		try {
			Scanner sc1 = new Scanner(fileName);
			String temp = "init";
			Scanner sc2 = new Scanner(temp);

			while (sc1.hasNextInt()) {
				temp = sc1.nextLine();
				rowCounter1++;
			}

			columns1 = temp.split(" ");
			mRows1 = rowCounter1;
			mColumns1 = columns1.length;
			sc1.nextLine();

			while (sc1.hasNextInt()) {
				temp = sc1.nextLine();
				rowCounter2++;
			}

			columns2 = temp.split(" ");
			mRows2 = rowCounter2;
			mColumns2 = columns2.length;

			sc1.close();
			sc2.close();
			
			if(rowCounter1 > 21){
				throw new IllegalArgumentException("Your Matrix is too big! You can only input up to 20 columns");
			}
			
			if (columns1.length != mRows2) {
				throw new IllegalArgumentException("Invalid matricies option " + columns1.length + " != " + mRows2);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int[][] readFirstMatrix() {
		int[][] newMatrix1 = new int[mRows1][mColumns1];

		try {
			Scanner scanner = new Scanner(fileName);

			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					matrixList1.add(scanner.nextInt());
				} else if (scanner.next().contains("**")) {
					scanner.close();
					break;
				} else {
					scanner.close();
					throw new IllegalArgumentException("Your character " + scanner.next() + " is an invalid!");
				}
			}
			scanner.close();

			for (int row = 0; row < newMatrix1.length; row++) {
				for (int column = 0; column < newMatrix1[0].length; column++) {
					newMatrix1[row][column] = matrixList1.removeFirst();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return newMatrix1;
	}

	public static int[][] readSecondMatrix() {
		int[][] newMatrix2 = new int[mRows2][mColumns2];
		int lineCounter = 0;

		try {
			Scanner scanner = new Scanner(fileName);

			while(lineCounter <= mRows1){
				scanner.nextLine();
				lineCounter++;
			}
			
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					matrixList2.add(scanner.nextInt());
				} else if (scanner.next().contains("**")) {
					scanner.close();
					break;
				} else {
					scanner.close();
					throw new IllegalArgumentException("Your character " + scanner.next() + " is an invalid!");
				}
			}
			scanner.close();

			for (int row = 0; row < newMatrix2.length; row++) {
				for (int column = 0; column < newMatrix2[0].length; column++) {
					newMatrix2[row][column] = matrixList2.removeFirst();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return newMatrix2;
	}
}
