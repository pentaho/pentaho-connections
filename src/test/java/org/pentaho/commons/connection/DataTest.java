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
package org.pentaho.commons.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pentaho.commons.connection.memory.MemoryMetaData;
import org.pentaho.commons.connection.memory.MemoryResultSet;

public class DataTest {

  @Test
  public void dataTransmuter() {
    int columns = 5;
    int rows = 8;
    int columnHeaderRows = 2;
    int rowHeaderColumns = 3;

    Object[][] columnHeaders = new Object[columnHeaderRows][columns];
    for ( int row = 0; row < columnHeaderRows; row++ ) {
      for ( int column = 0; column < columns; column++ ) {
        columnHeaders[row][column] = "CHead:" + row + "," + column; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    Object[][] rowHeaders = new Object[rows][rowHeaderColumns];
    for ( int row = 0; row < rows; row++ ) {
      for ( int column = 0; column < rowHeaderColumns; column++ ) {
        rowHeaders[row][column] = "RHead:" + row + "," + column; //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    MemoryResultSet resultSet = new MemoryResultSet( new MemoryMetaData( columnHeaders, rowHeaders ) );
    for ( int row = 0; row < rows; row++ ) {
      Object[] aRow = new Object[columns];
      for ( int column = 0; column < columns; column++ ) {
        aRow[column] = "Data: " + row + "," + column; //$NON-NLS-1$ //$NON-NLS-2$
      }
      resultSet.addRow( aRow );
    }
    String results = PentahoDataTransmuter.dump( resultSet );

    assertEquals( "\t\t\t\t\t\tCHead:1,0|\tCHead:1,1|\tCHead:1,2|\tCHead:1,3|\tCHead:1,4|\t\n" //$NON-NLS-1$
        + "\t\t\t\t\t\tCHead:0,0|\tCHead:0,1|\tCHead:0,2|\tCHead:0,3|\tCHead:0,4|\t\n" //$NON-NLS-1$
        + "RHead:0,2|\tRHead:0,1|\tRHead:0,0|\tData: 0,0|\tData: 0,1|\tData: 0,2|\tData: 0,3|\tData: 0,4|\t\n" //$NON-NLS-1$
        + "RHead:1,2|\tRHead:1,1|\tRHead:1,0|\tData: 1,0|\tData: 1,1|\tData: 1,2|\tData: 1,3|\tData: 1,4|\t\n" //$NON-NLS-1$
        + "RHead:2,2|\tRHead:2,1|\tRHead:2,0|\tData: 2,0|\tData: 2,1|\tData: 2,2|\tData: 2,3|\tData: 2,4|\t\n" //$NON-NLS-1$
        + "RHead:3,2|\tRHead:3,1|\tRHead:3,0|\tData: 3,0|\tData: 3,1|\tData: 3,2|\tData: 3,3|\tData: 3,4|\t\n" //$NON-NLS-1$
        + "RHead:4,2|\tRHead:4,1|\tRHead:4,0|\tData: 4,0|\tData: 4,1|\tData: 4,2|\tData: 4,3|\tData: 4,4|\t\n" //$NON-NLS-1$
        + "RHead:5,2|\tRHead:5,1|\tRHead:5,0|\tData: 5,0|\tData: 5,1|\tData: 5,2|\tData: 5,3|\tData: 5,4|\t\n" //$NON-NLS-1$
        + "RHead:6,2|\tRHead:6,1|\tRHead:6,0|\tData: 6,0|\tData: 6,1|\tData: 6,2|\tData: 6,3|\tData: 6,4|\t\n" //$NON-NLS-1$
        + "RHead:7,2|\tRHead:7,1|\tRHead:7,0|\tData: 7,0|\tData: 7,1|\tData: 7,2|\tData: 7,3|\tData: 7,4|\t\n\n", //$NON-NLS-1$
        results );
    String[] row1 = { "RHead:1,0", "RHead:1,1", "RHead:1,2" };
    String[] row2 = { "RHead:3,0", "RHead:3,1", "RHead:3,2" };
    String[] row3 = { "RHead:7,0", "RHead:7,1", "RHead:7,2" };
    String[] col1 = { "CHead:0,0", "CHead:1,0" };
    String[] col2 = { "CHead:0,3", "CHead:1,3" };
    String[][] rowsToInclude = { row1, row2, row3 };
    String[][] columnsToInclude = { col1, col2 };

    IPentahoResultSet testResults =
        PentahoDataTransmuter.transmute( resultSet, null, null, rowsToInclude, columnsToInclude, false );

    results = PentahoDataTransmuter.dump( testResults );

    assertEquals( "\t\t\t\t\t\tCHead:1,0|\tCHead:1,3|\t\n" //$NON-NLS-1$
        + "\t\t\t\t\t\tCHead:0,0|\tCHead:0,3|\t\n" //$NON-NLS-1$
        + "RHead:1,2|\tRHead:1,1|\tRHead:1,0|\tData: 1,0|\tData: 1,3|\t\n" //$NON-NLS-1$
        + "RHead:3,2|\tRHead:3,1|\tRHead:3,0|\tData: 3,0|\tData: 3,3|\t\n" //$NON-NLS-1$
        + "RHead:7,2|\tRHead:7,1|\tRHead:7,0|\tData: 7,0|\tData: 7,3|\t\n\n", //$NON-NLS-1$
        results );
  }

  @Test
  public void messages() {
    try {
      PentahoDataTransmuter.getCollapsedHeaders( 2, null, 'z' );
      fail();
    } catch ( Exception e ) {
      assertEquals( "PentahoDataTransmuter.ERROR_0001 - Invalid Axis", e.getMessage() ); //$NON-NLS-1$
    }
    try {
      PentahoDataTransmuter.getCollapsedHeaders( 1, null, 'z' );
      fail();
    } catch ( Exception e ) {
      assertEquals( "PentahoDataTransmuter.ERROR_0002 - ResultSet can't be null", e.getMessage() ); //$NON-NLS-1$
    }
  }
}
