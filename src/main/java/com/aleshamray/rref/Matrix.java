package com.aleshamray.rref;

import java.util.List;

public class Matrix {
  private List<Integer> matrix;
  private int rowCount;
  private List<Integer> rows;
  private int colCount;
  private List<Integer> cols;

  public Matrix(int rowCount, int colCount) {
    setRowCount(rowCount);
    setColCount(colCount);

  }

  public Matrix(int rowCount, int colCount, List<Integer> rows, List<Integer> cols) {

  }

  public void setRowCount(int rowCount) { this.rowCount = rowCount; }

  public void setColCount(int colCount) { this.colCount = colCount; }

  public int getRowCount() { return this.rowCount; }

  public int getColCount() { return this.colCount; }

  @Override
  public String toString() {
    return "";    
  }
}