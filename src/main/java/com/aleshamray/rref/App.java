package com.aleshamray.rref;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App {
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static boolean readyToQuit = false;

  public static void showMenuLoop() throws IOException {
    while (true) {
      System.out.println(
        "Create a new matrix (c)\n" + 
        "Update current matrix (u)\n" + 
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
        System.out.println("number of rows (n) and columns (m)\n"+
                           "[format: n m ... ex. 3 4]: ");
        
        int rowCount, colCount;
        try {
          String[] nums = reader.readLine().trim().split(" ");
          rowCount = Integer.valueOf(nums[0]);
          colCount = Integer.valueOf(nums[1]);
        } catch (ArrayIndexOutOfBoundsException a) {
          System.out.println("\nIncorrect format, try again!\n");
          break;
        }

        Matrix matrix = new Matrix(rowCount, colCount);
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
