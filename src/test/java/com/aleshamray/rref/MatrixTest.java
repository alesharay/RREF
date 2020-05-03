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
    assertThat(3).as("Should confirm the matrix row count is set correctly.")
                 .isEqualTo(matrix.getDimensions()[0]);

    assertThat(4).as("Should confirm the matrix column count is set correctly.")
                 .isEqualTo(matrix.getDimensions()[1]);

    assertThat("[1, 2, 3, 4]\n[2, 3, 4, 5]\n[3, 4, 5, 6]").as("Should confirm the matrix format is correct.")
                                                     .isEqualTo(matrix.toString());
  }

  @Test
  public void Test_Matrix_Validation() {
    assertThat(matrix.validateMatrix(rowCount, colCount, matrixBuild)).as("Should confirm the entered values are valid.")
                    .isTrue();
  }

  @Test
  public void Test_Row_Interchange_Operation() {
    assertThat(matrix.interchange(1, 3)).as("Should interchange rows 1 and 3.")
                    .isTrue();
  }

  @Test
  public void Test_Row_Scaling_Operation() {
    assertThat(matrix.scale(1, -2)).as("Should scale row 1 by -2.")
                                   .isTrue();
  }
}