/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/

package org.pentaho.commons.connection.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MemoryMetaDataTest {

  @Test
  public void testConstructor() {
    String[][] columnHeaders = new String[][] { { "col1", "col2" } };
    String[][] rowHeaders = new String[][] { { "row1", "row2" } };
    String columnNameFormat = "columnNameFormat";
    String[] columnTypes = new String[] { "columnType1", "columnType2"  };
    String[] columnNames = new String[] { "columnName1", "columnName2"  };
    String[] rowHeaderNames = new String[] { "rowHeaderName1", "rowHeaderName2"  };
    MemoryMetaData metadata = new MemoryMetaData( columnHeaders, rowHeaders, columnNameFormat, columnTypes, columnNames, rowHeaderNames );

    assertTrue( Arrays.equals( columnHeaders, metadata.getColumnHeaders() ) );
    assertTrue( Arrays.equals( rowHeaders, metadata.getRowHeaders() ) );
    assertEquals( columnNameFormat, metadata.getColumnNameFormat() );
    assertTrue( Arrays.equals( columnTypes, metadata.getColumnTypes() ) );
    assertTrue( Arrays.equals( columnNames, metadata.getFlattenedColumnNames() ) );
    assertTrue( Arrays.equals( rowHeaderNames, metadata.getRowHeaderNames() ) );
  }

  @Test
  public void testConstructor2() {
    String[][] columnHeaders = new String[][] { { "col1", "col2" } };
    String[][] rowHeaders = new String[][] { { "row1", "row2" } };
    String columnNameFormat = "columnNameFormat";
    String[] columnTypes = new String[] { "columnType1", "columnType2"  };
    String[] columnNames = new String[] { "columnName1", "columnName2"  };
    String[] rowHeaderNames = new String[] { "rowHeaderName1", "rowHeaderName2"  };
    MemoryMetaData metadataSample = new MemoryMetaData( columnHeaders, rowHeaders, columnNameFormat, columnTypes, columnNames, rowHeaderNames );
    MemoryMetaData metadataActual = new MemoryMetaData( metadataSample );

    assertTrue( Arrays.equals( columnHeaders, metadataActual.getColumnHeaders() ) );
    assertTrue( Arrays.equals( rowHeaders, metadataActual.getRowHeaders() ) );
    assertEquals( columnNameFormat, metadataActual.getColumnNameFormat() );
    assertTrue( Arrays.equals( columnTypes, metadataActual.getColumnTypes() ) );
    assertTrue( Arrays.equals( columnNames, metadataActual.getFlattenedColumnNames() ) );
    assertTrue( Arrays.equals( rowHeaderNames, metadataActual.getRowHeaderNames() ) );
  }

}
