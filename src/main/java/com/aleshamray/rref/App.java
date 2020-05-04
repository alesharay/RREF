package com.aleshamray.rref;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static boolean readyToQuit = false;
  private static Matrix matrix = new Matrix();
  private static ArrayList< ArrayList<Integer> > matrixBuild = new ArrayList< ArrayList<Integer> >();

  public static void showMenuLoop() throws IOException {
    while (true) {
      System.out.println(
        "Create a new matrix (c)\n" + 
        "Display the matrix (d)\n" +
        "Interchange (swap) two rows (i)\n" + 
        "Replace one row with the sum of itself and a scaled row (r)\n" +
        "Scale (multiply) a row by a given value (s)\n" +
        "Quit (q)\n\n" + 
        "OPTION: "
      );

      String option = reader.readLine();
      chooseOption(option);
      if (readyToQuit)
        break;
    }
  }

  public static void chooseOption(String option) throws IOException {
    switch (option.toLowerCase().trim()) {
      // if the option is to 'create a new matrix'
      case "c":
        matrixBuild = new ArrayList< ArrayList<Integer> >();
        System.out.print("number of rows (n) and columns (m)\n"+
                           "[format: m n ... ex. 3 4]: ");
        
        int rowCount, colCount;
        String[] nums;
        try {
          nums = reader.readLine().trim().split(" ");
          rowCount = Integer.valueOf(nums[0]);
          colCount = Integer.valueOf(nums[1]);
          
          if(rowCount == 0) {
            System.out.println();
            throw new ArrayIndexOutOfBoundsException();
          }

          System.out.println("\nEnter row values\n" + 
          "[example for a 3x3 matrix]\n" +
          "1 2 3\n" +
          "4 5 6\n" +
          "7 8 9\n\n" +
          "values: ");
          
          int tmpRowCount = rowCount;
          while(tmpRowCount-- > 0) {
             matrixBuild.add(new ArrayList<Integer>(Arrays.asList(reader.readLine().trim().split(" "))
                        .stream()
                        .map(x -> Integer.parseInt(x))
                        .collect(Collectors.toList())));
          }
          System.out.println();
          matrix = new Matrix(rowCount, colCount, matrixBuild);

          if(matrix.getDimensions()[0] == 0) {
            System.out.println();
            throw new ArrayIndexOutOfBoundsException();
          }
        } catch(ArrayIndexOutOfBoundsException a) {
            System.out.println("Incorrect format, try again!\n\n");
        } catch(NullPointerException n) {
          System.out.println("The values don't match the entered dimensions. Please try again!\n\n");
        } catch(NumberFormatException f) {
          System.out.println("You didn't enter enough numbers. Try again!\n\n");
        }
        break;
      // if the option is to display this matrix
      case "d":
        System.out.println("\n"+matrix.toString()+"\n");
        break;
      // if the option is to interchange two rows in the matrix
      case "i":
        System.out.print("\nWhich rows to swap\n"+
        "[format (count starts at 1): m n ... ex. 3 4]: ");

        int row1, row2;
        try {
          nums = reader.readLine().trim().split(" ");
          row1 = Integer.valueOf(nums[0]);
          row2 = Integer.valueOf(nums[1]);

          if(matrix.interchange(row1, row2)) {
            System.out.println("\nSuccessful! Display to view interchange.\n");
          } else {
            System.out.println("\nUnsuccessful, matrix is not valid. Try to rebuild!\n");
          }
        } catch (IndexOutOfBoundsException i) {
          System.out.println("\nRow values are invalid! Try again\n");
        }
        break;
      // if the option is to replace a row by itself and the sum of a scaled row
      case "r":
        System.out.print("\nReplace row, row to scale and add to replace row, scale factor?\n"+
        "[format (count starts at 1): m n o ... ex. 1 2 -.5]: ");

        try {
          nums = reader.readLine().trim().split(" ");
          int rowToReplace = Integer.valueOf(nums[0]);
          int rowToScaleAndAdd = Integer.valueOf(nums[1]);
          int scaleFactor = Integer.valueOf(nums[2]);

          System.out.printf("rowToReplace: %d\nrowToScaleAndAdd: %d\nScaleFactor: %d\n", rowToReplace, rowToScaleAndAdd, scaleFactor);

          if(matrix.replace(rowToReplace, rowToScaleAndAdd, scaleFactor)) {
            System.out.println("\nSuccessful! Display to view replace.\n");
          } else {
            System.out.println("\nUnsuccessful, matrix is not valid. Try to rebuild!\n");
          }
        } catch (IndexOutOfBoundsException i) {
          System.out.println("\nNot enough values! Try again\n");
        }
        break;
      // if the option is to scale a row by a given factor
      case "s":
        System.out.print("\nRow you would like to scale and by what factor?\n"+
        "[format: m n ... ex. 1 -2]: ");
        try { 
          nums = reader.readLine().trim().split(" ");
          int rowToScale = Integer.valueOf(nums[0]);
          int scaleFactor = Integer.valueOf(nums[1]);

          if(matrix.scale(rowToScale, scaleFactor)) {     
            System.out.println("\nSuccessful! Display to view scale.\n");
          } else {
            System.out.println("\nEither the matrix is not valid or the row to scale is not valid!\n");
          }
        } catch (ArrayIndexOutOfBoundsException a) {
          System.out.println("\nIncorrect format, try again!\n");
          break;
        }

        break;
      // if the option is to quit
      case "q":
        readyToQuit = true;
        break;
      // if a valid option is not chosen
      default:
        System.out.println("\nNot a valid option. Please try again!\n");
        break;
    }
  }

  public static void main(String[] args) throws IOException {
    showMenuLoop();
  }
}
