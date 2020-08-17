import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NearestNeighbor {

	public static void main(String[] args) {

		System.out.println("Programming Fundamentals");
		System.out.println("Name: Sebastian Rodriguez");
		System.out.println("PROGRAMMING ASSIGNMENT 3 \n");

		double[][] trainingData = new double[75][4];
		String[] trainingName = new String[75];
		double[][] testingData = new double[75][4];
		String[] testingName = new String[75];

		String name;
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter name of training file: ");
		name = scan.next();
		scanFile(name, trainingData, trainingName);
		System.out.print("Enter name of testing file: ");
		name = scan.next();
		scanFile(name, testingData, testingName);
		System.out.println();
		classify(testingData, testingName, trainingData, trainingName);
		scan.close();
	}	
	public static void scanFile(String fileName, double[][] data, String[] name) {

		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			int line = 0;
			while (scan.hasNext()) {
				String row = scan.nextLine();
				String[] dataRow = row.split(",");

				for (int i = 0; i < dataRow.length; i++) {
					if (i < 4) {
						data[line][i] = Double.parseDouble(dataRow[i]);
					} else {
						name[line] = dataRow[i];
					}
				}
				line++;
			}
			scan.close();
		} catch (FileNotFoundException exp) {
			System.out.println("Incorrect file. Please run program and try again with correct file.");
			System.exit(0);
		}
	}

	public static int dist(int num, double[][] data1, double[][] data2) {
		double dist = 0;
		double max = 10;
		int guess = 0;
		for (int i = 0; i < data2.length; i++) {
			for (int j = 0; j < 4; j++) {
				double a = Math.pow(data1[num][j] - data2[i][j], 2);
				dist += a;
			}
			dist = Math.sqrt(dist);
			if (dist < max) {
				max = dist;
				guess = i;
			}
			dist = 0;
		}
		return guess;
	}

	public static void classify(double[][] data1, String[] name1, double[][] data2, String[] name2) {
		int count = 0;
		System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");
		for (int i = 0; i < data1.length; i++) {
			int guess = dist(i, data1, data2);
			System.out.println((i + 1) + ": " + name1[i] + " " + name2[guess]);
			if (name1[i].equals(name2[guess])) {
				count++;
			}
		}
		double accuracy = count / 75.0;
		System.out.println("ACCURACY: " + accuracy);
	}

}