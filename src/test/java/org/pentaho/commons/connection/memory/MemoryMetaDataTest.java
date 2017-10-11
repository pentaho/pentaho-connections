/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */
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
