package com.aleshamray.rref;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class MatrixTest
{
  @Test
  public void Matrx_Created_With_3_Rows_and_4_Columns() {
    final Matrix matrix = new Matrix(3, 4);

    assertThat(matrix.getRowCount()).as("checkk that the row count equals 3")
                                    .isEqualTo(3);
    assertThat(matrix.getColCount()).as("check that the column count equals 4")
                                    .isEqualTo(5);
  }
}