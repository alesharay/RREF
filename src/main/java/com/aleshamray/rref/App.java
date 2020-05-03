package com.aleshamray.rref;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
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
        "Update current matrix (u)\n" + 
        "Display the matrix (d)\n" +
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
      case "c":
        System.out.print("number of rows (n) and columns (m)\n"+
                           "[format: n m ... ex. 3 4]: ");
        
        int rowCount, colCount;
        String[] nums;
        try {
          nums = reader.readLine().trim().split(" ");
          rowCount = Integer.valueOf(nums[0]);
          colCount = Integer.valueOf(nums[1]);
          
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
          matrix = new Matrix(rowCount, colCount, matrixBuild);
          if(matrix == null) {
            System.out.println("\nThe values don't match the entered dimensions. Please try again!\n\n");
          }
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("\nIncorrect format, try again!\n");
            break;
        }
        break;
      case "d":
        System.out.println(matrix.toString());
        break;
      case "u":
        System.out.print("number of rows (n) and columns (m)\n"+
                           "[format: n m ... ex. 3 4]: ");
        try { 
          nums = reader.readLine().trim().split(" ");
          rowCount = Integer.valueOf(nums[0]);
          colCount = Integer.valueOf(nums[1]);

          System.out.println("\nEnter row values\n" + 
          "[example for a 3x3 matrix]\n" +
          "1 2 3\n" +
          "4 5 6\n" +
          "7 8 9\n\n" +
          "values: ");
          
          int tmpRowCount = rowCount;
          matrixBuild.clear();
          while(tmpRowCount-- > 0) {
             matrixBuild.add(new ArrayList<Integer>(Arrays.asList(reader.readLine().trim().split(" "))
                                                        .stream()
                                                        .map(x -> Integer.parseInt(x))
                                                        .collect(Collectors.toList())));
          }
          
          if(!matrix.updateMatrix(rowCount, colCount, matrixBuild)) {         
            System.out.println("\nThe values don't match the entered dimensions. Please try again!\n\n");
          }
        } catch (ArrayIndexOutOfBoundsException a) {
          System.out.println("\nIncorrect format, try again!\n");
          break;
        }
        break;
      case "q":
        readyToQuit = true;
        break;
      default:
        System.out.println("\nNot a valid option. Please try again!\n\n");
        break;
    }
  }

  public static void main(String[] args) throws IOException {
    showMenuLoop();
  }
}
