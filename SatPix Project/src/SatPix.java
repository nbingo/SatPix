import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Nomi Ringach
 * @since Oct 13, 2015
 */
public class SatPix {

	public static void main(String[] args) throws IOException
	{
		boolean[][] booleanArr = fileToBoolArray("bigPicture2.txt");
		int sizeOfLargestPasture = 0, temp = 0;
		
		for (int row = 0; row < booleanArr.length; row++)
			for (int col = 0; col < booleanArr[0].length; col++)
				if ((temp = recursivelyMeasureAndMarkPasture(row, col, booleanArr)) > sizeOfLargestPasture)
					sizeOfLargestPasture = temp;

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigPictureOut3.txt")));
		out.println(sizeOfLargestPasture);
		System.out.println(sizeOfLargestPasture);
		out.close();
		}
	/**
	 * @param fileName The name of the file with the pasture.
	 * @return A 2D boolean array with all of the '*' turned to true and all of the '.' turned to false.
	 * @throws FileNotFoundException If {@link java.util.Scanner#nextLine() Scanner} or {@link java.io.File File} has an error in not finding the file.
	 * @throws IOException If Scanner or File has an error in reading the file, probably if the file isn't correct.
	 * @author Nomi Ringach
	 * @since Oct 12 2015
	 */
	private static boolean[][] fileToBoolArray(String fileName) throws FileNotFoundException, IOException
	{
		Scanner read = new Scanner(new File(fileName));
		int[] dimensions = {read.nextInt(), read.nextInt()};
		boolean[][] arr = new boolean[dimensions[1]][dimensions[0]];
		for (int i = 0; i < arr.length; i++)
		{
			char[] letters = read.nextLine().toCharArray();
			for (int j = 0; j < letters.length; j++)
				arr[i][j] = letters[j] == '*';
		}
		read.close();
		return arr;
	}

	/**
	 * @author Nomi Ringach
	 * @param row The row in which to check.
	 * @param col The column in which to check.
	 * @param arr The boolean array.
	 * @return The size of the pasture so far.
	 * @since Oct 13, 2015
	 */
	private static int recursivelyMeasureAndMarkPasture(int row, int col, boolean[][] arr)
	{
		if (row < arr.length 		&& 
				row > -1 			&& 
				col < arr[0].length && 
				col > -1 			&& 
				arr[row][col])
		{
			arr[row][col] = false;
			return 1 + 
					recursivelyMeasureAndMarkPasture(row + 1, col, arr) +
					recursivelyMeasureAndMarkPasture(row - 1, col, arr) + 
					recursivelyMeasureAndMarkPasture(row, col + 1, arr) + 
					recursivelyMeasureAndMarkPasture(row, col - 1, arr);
		}
		return 0;
	}
}