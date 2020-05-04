package com.aleshamray.rref;

import java.util.ArrayList;
import java.util.stream.*;

public class Matrix {
  private ArrayList< ArrayList<Integer> > matrix;
  private int rowCount;
  private int colCount;

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

  private boolean updateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      setDimensions(rowCount, colCount);
      setMatrix(matrix);
      return true;
    }
    return false;
  }

  public Matrix() {
    defaultMatrixBuilder();
    System.out.println("\n\n"+toString()+"\n\n");
  }
  
  public Matrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(!updateMatrix(rowCount, colCount, matrix)) {
      defaultMatrixBuilder();
    }
    System.out.println("\n\n"+toString()+"\n\n");
  }

  public void setDimensions(int rowCount, int colCount) {
    this.rowCount = rowCount;
    this.colCount = colCount;
  }

  public void setMatrix (ArrayList<ArrayList<Integer>> matrix) { 
    this.matrix = matrix; 
  }

  public int[] getDimensions() { return new int[]{rowCount, colCount}; }

  public boolean validateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(matrix.size() != rowCount ) return false; 
    for(ArrayList<Integer> list : matrix) {
      if(list.size() != colCount) return false;
    }
    return true;
  }

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

  public boolean scale(int row, int scaleFactor) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      if((row < 1) || (row > rowCount)) return false;
      ArrayList<Integer> rowToScale = matrix.get(--row);
      matrix.set(row, new ArrayList<Integer>(rowToScale.stream()
                                                       .map(x -> x *= scaleFactor)
                                                       .collect(Collectors.toList())
                                            )
                );
      System.out.println("\n\n"+toString()+"\n\n");
      return true;
    }
    return false;
  }

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