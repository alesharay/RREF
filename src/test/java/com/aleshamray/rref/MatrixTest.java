package com.aleshamray.rref;

import java.util.Arrays;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class MatrixTest
{
  private int rowCount;
  private int colCount;
  private ArrayList<ArrayList<Integer>> matrixBuild = new ArrayList<ArrayList<Integer>>();
  private Matrix matrix;
  {
    rowCount = 3;
    colCount = 4;
    matrixBuild = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
    ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(2,3,4,5));
    ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(3,4,5,6));
    matrixBuild.add(row1);
    matrixBuild.add(row2);
    matrixBuild.add(row3);
    matrix = new Matrix(rowCount, colCount, matrixBuild);
  }

  @Test
  public void Test_Matrix_Creation() {
    assertThat(3).as("Check that matrix row count is set to 3")
                 .isEqualTo(matrix.getDimensions()[0]);

    assertThat(4).as("Check that matrix colCount is set to 4")
                 .isEqualTo(matrix.getDimensions()[1]);

    assertThat("\n[1, 2, 3, 4]\n[2, 3, 4, 5]\n[3, 4, 5, 6]\n").as("check that the matrix has correct values and is built in the correct format")
                                                     .isEqualTo(matrix.toString());
  }

  @Test
  public void Test_Matrix_Validation() {
    assertThat(matrix.validateMatrix(rowCount, colCount, matrixBuild)).as("Check that the entered matrix is valid")
                    .isTrue();
  }
}