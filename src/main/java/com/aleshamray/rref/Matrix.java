package com.aleshamray.rref;

import java.util.ArrayList;
import java.util.stream.*;

public class Matrix {
  private ArrayList< ArrayList<Integer> > matrix;
  private int rowCount;
  private int colCount;

  /**
   * defaultMatrixBuilder is a private helper function to build the default matrix
   */
  private void defaultMatrixBuilder() {
    this.matrix = new ArrayList< ArrayList<Integer> >();
    this.rowCount = 0;
    this.colCount = 0;
    int tmpRowCount = 3;
    while(tmpRowCount-- > 0) {
      ArrayList<Integer> row = new ArrayList<Integer>();
      int tmpColCount = 3;
      while(tmpColCount-- > 0) {
        row.add(0);
      }
      matrix.add(row);
    }
  }

  /**
   * updateMatrix is a helper method to update the matrix with different dimensions
   * @param rowCount the number of rows in the updated matrix
   * @param colCount the number of columns in the updated matrix
   * @param matrix the updated values of the matrix
   * @return true if the matrix was successfully updated; false otherwise
   */
  private boolean updateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      setDimensions(rowCount, colCount);
      setMatrix(matrix);
      return true;
    }
    return false;
  }

  /**
   * Default Constructor
   */
  public Matrix() {
    defaultMatrixBuilder();
    System.out.println("\n\n"+toString()+"\n\n");
  }

  /**
   * Parameterized Constructor
   * @param rowCount the number of rows in the matrix
   * @param colCount the number of columns in the matrix
   * @param matrix the values of the matrix
   */  
  public Matrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(!updateMatrix(rowCount, colCount, matrix)) {
      defaultMatrixBuilder();
    }
    System.out.println("\n\n"+toString()+"\n\n");
  }

  /**
   * setDimensions sets the m x n dimensions of the matrix
   * @param rowCount the number of rows in the matrix
   * @param colCount the number of columns in the matrix
   */
  public void setDimensions(int rowCount, int colCount) {
    this.rowCount = rowCount;
    this.colCount = colCount;
  }

  /**
   * setMatrix sets the values of the matrix
   * @param matrix the values of the matrix
   */
  public void setMatrix (ArrayList<ArrayList<Integer>> matrix) { 
    this.matrix = matrix; 
  }

  /**
   * getDimensions returns the m x n dimensions of the matrix
   * @return the rows as the first value and columns as the second
   */
  public int[] getDimensions() { return new int[]{rowCount, colCount}; }

  /**
   * validateMatrix confirms that the given matrix with values matches the given dimensions
   * @param rowCount the number of rows of the given matrix
   * @param colCount the number of columns of the given matrix
   * @param matrix the values of the viven matrix
   * @return true if the given matrix is valid; false othewise
   */
  public boolean validateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(matrix.size() != rowCount ) return false; 
    for(ArrayList<Integer> list : matrix) {
      if(list.size() != colCount) return false;
    }
    return true;
  }

  /**
   * interchange swaps to rows in the matrix
   * @param row1 the first row to swap
   * @param row2 the second row to swap
   * @return true if the rows swapped successfully; false otherwise
   */
  public boolean interchange(int row1, int row2) {
    if(validateMatrix(this.rowCount, this.colCount, this.matrix)) {
      ArrayList<Integer> originalRow = matrix.get(--row1);
      ArrayList<Integer> swapRow = matrix.get(--row2);
      matrix.set(row1, swapRow);
      matrix.set(row2, originalRow);
      System.out.println("\n\n"+toString()+"\n\n");
      return true;
    }
    return false;
  }

  /**
   * scale resizes a given row based on a given factor
   * @param row row to be resized
   * @param scaleFactor factor to resize by
   * @return true if the row scaled successfully; false otherwise
   */
  public boolean scale(int row, int scaleFactor) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      if((row < 1) || (row > rowCount)) return false;
      ArrayList<Integer> rowToScale = matrix.get(--row);
      matrix.set(row, new ArrayList<>(rowToScale.stream()
                                                       .map(x -> x *= scaleFactor)
                                                       .collect(Collectors.toList())
                                            )
                );
      System.out.println("\n\n"+toString()+"\n\n");
      return true;
    }
    return false;
  }
  
  /**
   * replace scales a given row by the given factor and replaces the specified row with the new scaled row. The scaled row retains it's original values
   * @param rowToReplace the row to be replaced
   * @param rowToAdd the row to scale and replace as new row 
   * @param scaleFactor the factor to scale by
   * @return true if the row replacement is successful; false otherwise
   */
  public boolean replace(int rowToReplace, int rowToAdd, int scaleFactor) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      ArrayList<Integer> replaceRow = matrix.get(rowToReplace-1);
      ArrayList<Integer> addRow = matrix.get(rowToAdd-1);
      scale(rowToAdd, scaleFactor);
      ArrayList<Integer> scaledAddRow = matrix.get(rowToAdd-1);
      ArrayList<Integer> summedReplaceRow = new ArrayList<Integer>();
      for(int i = 0; i < replaceRow.size(); i++) {
        summedReplaceRow.add(replaceRow.get(i) + scaledAddRow.get(i));
      }
      matrix.set(rowToReplace-1, summedReplaceRow);
      matrix.set(rowToAdd-1, addRow);
      System.out.println("\n\n"+toString()+"\n\n");
      return true;
    }
    return false;
  }

  /**
   * toString shows the formatted matrix as a text value
   */
  @Override
  public String toString() {
    if(matrix == null) return null;

    String matrixString = "";
    for(ArrayList<Integer> list : matrix) {
      matrixString += String.valueOf(list) + "\n";
    }

    return matrixString.trim(); 
  }
}