package com.aleshamray.rref;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class MatrixTest
{
  @Test
  public void Test_Matrix_Creation() {
    int rowCount = 3;
    int colCount = 4;
    ArrayList<ArrayList<Integer>> matrixBuild = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(2,3,4));
    ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(3,4,5));
    ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(4,5,6));
    matrixBuild.add(row1);
    matrixBuild.add(row2);
    matrixBuild.add(row3);
    Matrix matrix = new Matrix(rowCount, colCount, matrixBuild);

    assertThat(3).as("Check that matrix row count is set to 3")
                 .isEqualTo(matrix.getRowCount());

    assertThat(4).as("Check that matrix colCount is set to 4")
                 .isEqualTo(matrix.getColCount());

    assertThat("\n[2, 3, 4]\n[3, 4, 5]\n[4, 5, 6]\n").as("check that the matrix has correct values and is built in the correct format")
                                                 .isEqualTo(matrix.toString());

  }
}