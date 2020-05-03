package com.aleshamray.rref;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

public class Matrix {
  private ArrayList< ArrayList<Integer> > matrix;
  private int rowCount;
  private int colCount;

  public Matrix() {
    this.matrix = null;
  }
  
  public Matrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
   if(!updateMatrix(rowCount, colCount, matrix)) this.matrix = null;
  }

  public Matrix(int rowCount, int colCount, List<Integer> rows, List<Integer> cols) {

  }

  public void setDimensions(int rowCount, int colCount) {
    this.rowCount = rowCount;
    this.colCount = colCount;
  }

  public void setMatrix (ArrayList<ArrayList<Integer>> matrix) { this.matrix = matrix; }

  public int[] getDimensions() { return new int[]{rowCount, colCount}; }

  public boolean updateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(validateMatrix(rowCount, colCount, matrix)) {
      setDimensions(rowCount, colCount);
      setMatrix(matrix);
      return true;
    }
    return false;
  }

  public boolean validateMatrix(int rowCount, int colCount, ArrayList<ArrayList<Integer>> matrix) {
    if(matrix.size() != rowCount ) return false; 
    for(ArrayList<Integer> list : matrix) {
      if(list.size() != colCount) return false;
    }
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