package com.aleshamray.rref;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class Matrix {
  private ArrayList< ArrayList<Integer> > matrix;
  private int rowCount;
  private int colCount;

  public Matrix() {
    matrix = new ArrayList< ArrayList<Integer> >();
    rowCount = 3;
    colCount = 3;
    while(rowCount-- > 0) {
      ArrayList<Integer> row = new ArrayList<Integer>();
      int tmpColCount = colCount;
      while(tmpColCount-- > 0) {
        row.add(0);
      }
      matrix.add(row);
    }
  }
  
  public Matrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    updateMatrix(rowCount, colCount, matrix);
  }

  public Matrix(int rowCount, int colCount, List<Integer> rows, List<Integer> cols) {

  }

  public void setRowCount(int rowCount) { this.rowCount = rowCount; }

  public void setColCount(int colCount) { this.colCount = colCount; }

  public void setMatrix (ArrayList<ArrayList<Integer>> matrix) { this.matrix = matrix; }

  public int getRowCount() { return this.rowCount; }

  public int getColCount() { return this.colCount; }

  public boolean updateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    setRowCount(rowCount);
    setColCount(colCount);
    setMatrix(matrix);
    return true;
  }

  @Override
  public String toString() {
    if(matrix == null) return null;

    String matrixString = "\n";
    for(ArrayList<Integer> list : matrix) {
      matrixString += String.valueOf(list) + "\n";
    }

    return matrixString; 
  }
}