package satellite;

import java.io.*;

public class Satellite {

    static int[][] oldImage;
    static int[][] newImage;
    static int noOfRows, noOfCols;

    public static void main(String[] args) {
        try {
           // {read from input.txt}
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            noOfRows = Integer.parseInt(reader.readLine().trim());
            noOfCols = Integer.parseInt(reader.readLine().trim());

            oldImage = new int[noOfRows][noOfCols];
            newImage = new int[noOfRows][noOfCols];

            readImage(reader, oldImage);
            readImage(reader, newImage);

            reader.close();

            int x1 = findBoundary(0, noOfRows, 1, true);
            int y1 = findBoundary(0, noOfCols, 1, false);
            int x2 = findBoundary(noOfRows - 1, -1, -1, true);
            int y2 = findBoundary(noOfCols - 1, -1, -1, false);

            // {output}
            if (x1 > x2 || y1 > y2) {
                System.out.println("The two images are the same");
            } else {
                x1++;x2++;y1++;y2++;
                System.out.println(x1 + " " + y1 + " " + (x2) + " " + (y2));
            }

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    static void readImage(BufferedReader reader, int[][] image) throws IOException {
        for (int row = 0; row < noOfRows; row++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            for (int col = 0; col < noOfCols; col++) {
                 image[row][col] = Integer.parseInt(parts[col]);
            }
        }
    }

    static int findBoundary(int start, int end, int step, boolean row) {
        int i = start;
        while (i != end) {
            if (row && !equalRows(i)) {
                break;
            }
            if (!row && !equalCols(i)) {
                break;
            } 
            i += step;
        }

        return i;
    }


    // {check if a row is equal in both images}
    public static boolean equalRows(int row) {
        for (int col = 0; col < noOfCols; col++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }

    // {check if a column is equal in both images}
    public static boolean equalCols(int col) {
        for (int row = 0; row < noOfRows; row++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }
}