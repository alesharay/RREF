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
    assertThat(matrix.getDimensions()[0])
    .as("Should confirm the matrix row count is set correctly.")
    .isEqualTo(3);

    assertThat(matrix.getDimensions()[1])
    .as("Should confirm the matrix column count is set correctly.")
    .isEqualTo(4);

    assertThat(matrix.toString())
    .as("Should confirm the matrix format is correct.")
    .isEqualTo("[1, 2, 3, 4]\n[2, 3, 4, 5]\n[3, 4, 5, 6]");
  }

  @Test
  public void Test_Matrix_Validation() {
    assertThat(matrix.validateMatrix(rowCount, colCount, matrixBuild))
    .as("Should confirm the entered values are valid.")
    .isTrue();
  }

  @Test
  public void Test_Row_Interchange_Operation() {
    assertThat(matrix.interchange(1, 3))
    .as("Should interchange rows 1 and 3.")
    .isTrue();
  }

  @Test
  public void Test_Row_Scaling_Operation() {
    assertThat(matrix.scale(1, -2))
    .as("Should scale row 1 by -2.")
    .isTrue();
  }

  @Test
  public void Test_Row_Replace_Operation() {
    assertThat(matrix.replace(1, 2, -2))
    .as("Should replace row 1 with the sum of itself and row 2 scaled by -2")
    .isTrue();

    matrixBuild = new ArrayList<ArrayList<Integer>>(Arrays.asList(
      new ArrayList<Integer>(Arrays.asList(1,1,1)),
      new ArrayList<Integer>(Arrays.asList(2,2,2)),
      new ArrayList<Integer>(Arrays.asList(3,3,3))
      )
    );

    matrix = new Matrix(3, 3, matrixBuild);
    matrix.replace(2, 1, -2);
    assertThat(matrix.toString())
    .as("Should replace row 2 with -2*row1 resulting in row 2 being all 0s")
    .isEqualTo("[1, 1, 1]\n[0, 0, 0]\n[3, 3, 3]");
  }

  @Test
  public void Test_Specific_Matrix_Entry() {
    assertThat(matrix.getEntry(1, 2))
    .as("Should confirm the specific entry (0 indexed) at row zero, column one is 2")
    .isEqualTo(2);

    assertThat(matrix.getEntry(2, 3))
    .as("Should confirm the specific entry (1 indexed) at row one, column three is 4")
    .isEqualTo(4);
  }

  @Test
  public void Test_Zero_Row_Swap() {
    matrixBuild = new ArrayList<ArrayList<Integer>>(Arrays.asList(
      new ArrayList<Integer>(Arrays.asList(0,0,0)),
      new ArrayList<Integer>(Arrays.asList(0,0,0)),
      new ArrayList<Integer>(Arrays.asList(3,3,3))
      )
    );
    matrix = new Matrix(3, 3, matrixBuild);
    matrix.moveZeroRowsToEnd();
    assertThat(matrix.toString())
    .as("Should move all zero rows to the end of the matrix")
    .isEqualTo("[3, 3, 3]\n[0, 0, 0]\n[0, 0, 0]");
  }
}