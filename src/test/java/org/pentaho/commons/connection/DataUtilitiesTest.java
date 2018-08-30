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
 * Copyright (c) 2002-2018 Hitachi Vantara..  All rights reserved.
 */
package org.pentaho.commons.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.pentaho.commons.connection.memory.MemoryMetaData;
import org.pentaho.commons.connection.memory.MemoryResultSet;

public class DataUtilitiesTest {

  @Test
  public void testPivotDimensions() {
    Object[][] data = new String[][]{ {"one-one", "one-two", "one-three" }, { "two-one", "two-two", "two-three" } };
    Object [][] pivotData = DataUtilities.pivotDimensions( data );
    assertEquals( "two-one", pivotData[0][1] );
    assertEquals( "one-two", pivotData[1][0] );
  }

  @Test
  public void testFilterDataByRows() {
    Object[][] data = new String[][]{ {"one-one", "one-two", "one-three" }, { "two-one", "two-two", "two-three" } };
    Object [][] filterData = DataUtilities.filterDataByRows( data, new Integer[] { 1 } );
    assertEquals( "two-one", filterData[0][0] );

    filterData = DataUtilities.filterDataByRows( data, new Integer[] { 0, 1 } );
    assertEquals( "one-one", filterData[0][0] );

    filterData = DataUtilities.filterDataByRows( data, null );
    assertEquals( "one-one", filterData[0][0] );
  }

  @Test
  public void testFilterDataByColumns() {
    Object[][] data = new String[][]{ {"one-one", "one-two", "one-three" }, { "two-one", "two-two", "two-three" } };
    Object [][] filterData = DataUtilities.filterDataByColumns( data, new Integer[] { 1 } );
    assertEquals( "one-two", filterData[0][0] );

    filterData = DataUtilities.filterDataByColumns( data, new Integer[] { 1, 2 } );
    assertEquals( "one-two", filterData[0][0] );
    assertEquals( "two-two", filterData[1][0] );

    filterData = DataUtilities.filterDataByColumns( data, null );
    assertEquals( "one-one", filterData[0][0] );
  }

  @Test
  public void testFilterData() {
    Object[][] data = new String[][]{ {"one-one", "one-two", "one-three" }, { "two-one", "two-two", "two-three" } };
    Object [][] filterData = DataUtilities.filterData( data, new Integer[] { 0 }, new Integer[] { 1 } );
    assertEquals( "one-two", filterData[0][0] );

    filterData = DataUtilities.filterData( data, new Integer[] {1}, new Integer[] { 1, 2 } );
    assertEquals( "two-two", filterData[0][0] );
    assertEquals( "two-three", filterData[0][1] );

    filterData = DataUtilities.filterData( data, null, null );
    assertEquals( "one-one", filterData[0][0] );
  }

  @Test
  public void testGetXMLString() {
    Object[][] data = new String[][]{ {"one-one", "one-two", "one-three" }, { "two-one", "two-two", "two-three" } };
    Object[][] columnHeaders = new String[][] { {"col-1-1", "col-1-2", "col-1-3"},
      { "col 2-1", "col 2-2", "col 2-3" } };
    Object[][] rowHeaders = new String[][] { {"row-1-1", "row-1-2", "row-1-3"}, { "row-2-1", "row-2-2", "row-2-3" } };
    MemoryMetaData metaData = new MemoryMetaData( columnHeaders, rowHeaders );
    MemoryResultSet mrs = new MemoryResultSet( metaData );
    mrs.addRow( data[0] );
    mrs.addRow( data[1] );
    String xml = DataUtilities.getXMLString( mrs );
    assertTrue( xml.indexOf( "<col-1-1>one-one</col-1-1>" ) > 0 );
    assertTrue( xml.indexOf( "<col-1-3>two-three</col-1-3>" ) > 0 );
  }
}
