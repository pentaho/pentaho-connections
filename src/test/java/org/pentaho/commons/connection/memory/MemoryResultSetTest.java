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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.junit.Test;
import org.pentaho.commons.connection.IPentahoMetaData;

@SuppressWarnings( { "all" } )
public class MemoryResultSetTest {

  @Test
  public void testMetadata1() {
    List columnNames = new ArrayList();
    columnNames.add( "col1" );
    columnNames.add( "col2" );
    MemoryMetaData metadata = new MemoryMetaData( columnNames );

    assertNull( metadata.getColumnTypes() );
    metadata.setColumnTypes( new String[] { "type1", "type2" } );

    assertNull( "row headers are wrong", metadata.getRowHeaders() );
    assertNotNull( "col headers are wrong", metadata.getColumnHeaders() );
    Object[][] headers = metadata.getColumnHeaders();

    assertEquals( 1, headers.length );
    assertEquals( 2, headers[0].length );
    assertEquals( "col1", headers[0][0] );
    assertEquals( "col2", headers[0][1] );

    String[] types = metadata.getColumnTypes();
    assertNotNull( types );

    assertEquals( 2, types.length );
    assertEquals( "type1", types[0] );
    assertEquals( "type2", types[1] );

    assertEquals( 0, metadata.getColumnIndex( "col1" ) );
    assertEquals( 1, metadata.getColumnIndex( "col2" ) );
    assertEquals( -1, metadata.getColumnIndex( "bogus" ) );
  }

  @Test
  public void testMetadata2() {

    String[][] colHeaders = new String[][] { { "col1", "col2" } };

    String[][] rowHeaders = new String[][] { { "row1" }, { "row2" } };

    MemoryMetaData metadata = new MemoryMetaData( colHeaders, rowHeaders );

    assertNull( metadata.getColumnTypes() );
    metadata.setColumnTypes( new String[] { "type1", "type2" } );

    assertNotNull( "row headers are wrong", metadata.getRowHeaders() );
    assertNotNull( "col headers are wrong", metadata.getColumnHeaders() );
    Object[][] headers = metadata.getColumnHeaders();

    assertEquals( 1, headers.length );
    assertEquals( 2, headers[0].length );
    assertEquals( "col1", headers[0][0] );
    assertEquals( "col2", headers[0][1] );

    headers = metadata.getRowHeaders();

    assertEquals( 2, headers.length );
    assertEquals( 1, headers[0].length );
    assertEquals( 1, headers[1].length );
    assertEquals( "row1", headers[0][0] );
    assertEquals( "row2", headers[1][0] );

  }

  @Test
  public void testNullMetadata() {

    MemoryMetaData metadata = new MemoryMetaData( null, null );

    assertNull( metadata.getColumnTypes() );

    assertNull( "row headers are wrong", metadata.getRowHeaders() );
    assertNull( "col headers are wrong", metadata.getColumnHeaders() );

    assertEquals( 0, metadata.getColumnCount() );
    assertEquals( -1, metadata.getColumnIndex( "col1" ) );
    assertEquals( -1, metadata.getRowIndex( "row1" ) );
    assertEquals( -1, metadata.getColumnIndex( new String[] { "col1" } ) );
  }

  @Test
  public void testConstructor1() {

    MemoryResultSet data = new MemoryResultSet();

    assertTrue( data.isScrollable() );
    IPentahoMetaData metadata = data.getMetaData();
    assertNull( "metadata should be null", metadata );
    assertEquals( "row count is wrong", 0, data.getRowCount() );

    metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );
    data.setMetaData( metadata );

    assertEquals( metadata, data.getMetaData() );

    assertEquals( 2, data.getColumnCount() );
  }

  @Test
  public void testConstructor2() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    assertEquals( metadata, data.getMetaData() );

  }

  @Test
  public void testAddRow() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );

  }

  @Test
  public void testSetRows() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    List<Object[]> rows = new ArrayList<Object[]>();

    rows.add( new Object[] { "a", new Integer( 1 ) } );
    rows.add( new Object[] { "b", new Integer( 2 ) } );
    rows.add( new Object[] { "c", new Integer( 3 ) } );

    data.setRows( rows );

    assertNotNull( data.getRows() );
    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );

  }

  @Test
  public void testIterators() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );

    assertEquals( "a", data.next()[0] );
    assertEquals( "b", data.next()[0] );
    assertEquals( "c", data.next()[0] );
    assertNull( data.next() );

    data.beforeFirst();

    assertEquals( "a", data.next()[0] );
    assertEquals( "b", data.next()[0] );
    assertEquals( "c", data.next()[0] );
    assertNull( data.next() );

    data.close();

    assertEquals( "a", data.next()[0] );
    assertEquals( "b", data.next()[0] );
    assertEquals( "c", data.next()[0] );
    assertNull( data.next() );

    data.closeConnection();

    assertEquals( "a", data.next()[0] );
    assertEquals( "b", data.next()[0] );
    assertEquals( "c", data.next()[0] );
    assertNull( data.next() );

    data.dispose();

    assertEquals( "a", data.next()[0] );
    assertEquals( "b", data.next()[0] );
    assertEquals( "c", data.next()[0] );
    assertNull( data.next() );

  }

  @Test
  public void testGetDataColumn() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] col = data.getDataColumn( 0 );
    assertEquals( 3, col.length );
    assertEquals( "a", col[0] );
    assertEquals( "b", col[1] );
    assertEquals( "c", col[2] );

    col = data.getDataColumn( 1 );
    assertEquals( 3, col.length );
    assertEquals( 1, col[0] );
    assertEquals( 2, col[1] );
    assertEquals( 3, col[2] );
  }

  @Test
  public void testGetDataRow() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] row = data.getDataRow( 0 );
    assertEquals( 2, row.length );
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );
    row = data.getDataRow( 1 );
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );
    row = data.getDataRow( 2 );
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    assertNull( data.getDataRow( 99 ) );
  }

  @Test
  public void testPeek() {

    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] row = data.peek();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peek();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peek();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.next();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peek();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.peek();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.next();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.peek();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peek();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.next();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peek();
    assertNull( row );

    row = data.peek();
    assertNull( row );

    row = data.peek();
    assertNull( row );

    row = data.next();
    assertNull( row );

    data.beforeFirst();
    row = data.peek();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

  }

  @Test
  public void testPeekFlattened1() {

    MemoryMetaData metadata =
        new MemoryMetaData( new String[][] { { "col1", "col2" } }, new String[][] { { "2009", "item1" },
          { "2009", "item2" }, { "2008", "item1" } } );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.nextFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.nextFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.peekFlattened();
    assertEquals( "2008", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "c", row[2] );
    assertEquals( 3, row[3] );

    row = data.peekFlattened();
    assertEquals( "2008", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "c", row[2] );
    assertEquals( 3, row[3] );

    row = data.nextFlattened();
    assertEquals( "2008", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "c", row[2] );
    assertEquals( 3, row[3] );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.nextFlattened();
    assertNull( row );

    data.beforeFirst();
    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

  }

  @Test
  public void testPeekFlattened2() {

    // test that the flattened next() and peek() work like the regular ones
    // if there are no row headers
    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] row = data.peekFlattened();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peekFlattened();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peekFlattened();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.nextFlattened();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

    row = data.peekFlattened();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.peekFlattened();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.nextFlattened();
    assertEquals( "b", row[0] );
    assertEquals( 2, row[1] );

    row = data.peekFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peekFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.nextFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.nextFlattened();
    assertNull( row );

    data.beforeFirst();
    row = data.peekFlattened();
    assertEquals( "a", row[0] );
    assertEquals( 1, row[1] );

  }

  @Test
  public void testPeekFlattened3() {

    // test what happens when there are fewer row headers than rows
    MemoryMetaData metadata =
        new MemoryMetaData( new String[][] { { "col1", "col2" } }, new String[][] { { "2009", "item1" },
          { "2009", "item2" } } );

    MemoryResultSet data = new MemoryResultSet( metadata );

    data.addRow( new Object[] { "a", new Integer( 1 ) } );
    data.addRow( new Object[] { "b", new Integer( 2 ) } );
    data.addRow( new Object[] { "c", new Integer( 3 ) } );

    Object[] row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.nextFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.nextFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item2", row[1] );
    assertEquals( "b", row[2] );
    assertEquals( 2, row[3] );

    row = data.peekFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peekFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.nextFlattened();
    assertEquals( "c", row[0] );
    assertEquals( 3, row[1] );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.peekFlattened();
    assertNull( row );

    row = data.nextFlattened();
    assertNull( row );

    data.beforeFirst();
    row = data.peekFlattened();
    assertEquals( "2009", row[0] );
    assertEquals( "item1", row[1] );
    assertEquals( "a", row[2] );
    assertEquals( 1, row[3] );

  }

  @Test
  public void testColumnNames1() {

    MemoryMetaData metadata =
        new MemoryMetaData( new String[][] { { "col1", "col2" } }, new String[][] { { "2009", "item1" },
          { "2009", "item2" }, { "2008", "item1" }, } );

    MemoryResultSet data = new MemoryResultSet( metadata );

    metadata.setRowHeaderNames( new String[] { "Year", "Item" } );

    metadata.generateColumnNames();

    String[] tmp = metadata.getRowHeaderNames();
    assertEquals( "Year", tmp[0] );
    assertEquals( "Item", tmp[1] );

    String[] columnNames = metadata.getFlattenedColumnNames();

    assertEquals( "wrong number of column names", 4, columnNames.length );
    assertEquals( "wrong column name", "Year", columnNames[0] );
    assertEquals( "wrong column name", "Item", columnNames[1] );
    assertEquals( "wrong column name", "col1", columnNames[2] );
    assertEquals( "wrong column name", "col2", columnNames[3] );

  }

  @Test
  public void testColumnNames2() {

    MemoryMetaData metadata =
        new MemoryMetaData( new String[][] { { "col1", "col2" }, { "subcol1", "subcol2" } }, new String[][] {
          { "2009", "item1" }, { "2009", "item2" }, { "2008", "item1" }, } );

    MemoryResultSet data = new MemoryResultSet( metadata );

    metadata.setRowHeaderNames( new String[] { "Year", "Item" } );
    metadata.setColumnNameFormat( "{0}::{1}" );
    metadata.generateColumnNames();

    String[] columnNames = metadata.getFlattenedColumnNames();

    assertEquals( "wrong number of column names", 4, columnNames.length );
    assertEquals( "wrong column name", "Year", columnNames[0] );
    assertEquals( "wrong column name", "Item", columnNames[1] );
    assertEquals( "wrong column name", "col1::subcol1", columnNames[2] );
    assertEquals( "wrong column name", "col2::subcol2", columnNames[3] );

    metadata.setFlattenedColumnNames( new String[] { "yr", "item", "c1", "c2" } );

    columnNames = metadata.getFlattenedColumnNames();

    assertEquals( "wrong number of column names", 4, columnNames.length );
    assertEquals( "wrong column name", "yr", columnNames[0] );
    assertEquals( "wrong column name", "item", columnNames[1] );
    assertEquals( "wrong column name", "c1", columnNames[2] );
    assertEquals( "wrong column name", "c2", columnNames[3] );

  }

  @Test
  public void testCreateList() {

    List items = new ArrayList();
    items.add( "a" );
    items.add( "b" );
    items.add( "c" );

    MemoryResultSet data = MemoryResultSet.createList( "col1", items );
    assertNotNull( data.getMetaData() );
    assertNotNull( data.getMetaData().getColumnHeaders() );
    assertNull( data.getMetaData().getRowHeaders() );

    assertEquals( 1, data.getMetaData().getColumnCount() );
    assertEquals( 1, data.getMetaData().getColumnHeaders().length );
    assertEquals( 1, data.getMetaData().getColumnHeaders()[0].length );
    assertEquals( "col1", data.getMetaData().getColumnHeaders()[0][0] );

    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );

  }

  @Test
  public void testCreateFromArrays() {

    MemoryResultSet data =
        MemoryResultSet.createFromArrays( new String[][] { { "col1", "col2" } }, new Object[][] { { "a", 1 },
          { "b", 2 }, { "c", 3 } } );

    assertNotNull( data.getMetaData() );
    assertNotNull( data.getMetaData().getColumnHeaders() );
    assertNull( data.getMetaData().getRowHeaders() );

    assertEquals( 2, data.getMetaData().getColumnCount() );
    assertEquals( 1, data.getMetaData().getColumnHeaders().length );
    assertEquals( 2, data.getMetaData().getColumnHeaders()[0].length );
    assertEquals( "col1", data.getMetaData().getColumnHeaders()[0][0] );
    assertEquals( "col2", data.getMetaData().getColumnHeaders()[0][1] );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );

  }

  @Test
  public void testCreateFromLists() {

    List colHeaders = new ArrayList();
    colHeaders.add( "col1" );
    colHeaders.add( "col2" );

    List<List<Object>> dataList = new ArrayList<List<Object>>();
    List<Object> row = new ArrayList<Object>();
    row.add( "a" );
    row.add( 1 );
    dataList.add( row );
    row = new ArrayList<Object>();
    row.add( "b" );
    row.add( 2 );
    dataList.add( row );
    row = new ArrayList<Object>();
    row.add( "c" );
    row.add( 3 );
    dataList.add( row );

    MemoryResultSet data = MemoryResultSet.createFromLists( colHeaders, dataList );

    assertNotNull( data.getMetaData() );
    assertNotNull( data.getMetaData().getColumnHeaders() );
    assertNull( data.getMetaData().getRowHeaders() );

    assertEquals( 2, data.getMetaData().getColumnCount() );
    assertEquals( 1, data.getMetaData().getColumnHeaders().length );
    assertEquals( 2, data.getMetaData().getColumnHeaders()[0].length );
    assertEquals( "col1", data.getMetaData().getColumnHeaders()[0][0] );
    assertEquals( "col2", data.getMetaData().getColumnHeaders()[0][1] );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );

  }

  @Test
  public void testMemoryCopy() {
    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );
    testCopyMetadata( metadata );
  }

  @Test
  public void testMemoryCopyIPentahoMetaData() {
    IPentahoMetaData metadata = mock( IPentahoMetaData.class );
    when( metadata.getColumnHeaders() ).thenReturn( new String[][] { { "col1", "col2" } } );
    when( metadata.getColumnCount() ).thenReturn( 2 );
    testCopyMetadata( metadata );
  }

  private void testCopyMetadata( IPentahoMetaData metadata ) {
    MemoryResultSet data1 = new MemoryResultSet( metadata );

    data1.addRow( new Object[] { "a", new Integer( 1 ) } );
    data1.addRow( new Object[] { "b", new Integer( 2 ) } );
    data1.addRow( new Object[] { "c", new Integer( 3 ) } );

    MemoryResultSet data = (MemoryResultSet) data1.memoryCopy();

    assertNotNull( data.getMetaData() );
    assertNotNull( data.getMetaData().getColumnHeaders() );
    assertNull( data.getMetaData().getRowHeaders() );

    assertEquals( 2, data.getMetaData().getColumnCount() );
    assertEquals( 1, data.getMetaData().getColumnHeaders().length );
    assertEquals( 2, data.getMetaData().getColumnHeaders()[0].length );
    assertEquals( "col1", data.getMetaData().getColumnHeaders()[0][0] );
    assertEquals( "col2", data.getMetaData().getColumnHeaders()[0][1] );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( 1, data.getValueAt( 0, 1 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( 2, data.getValueAt( 1, 1 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( 3, data.getValueAt( 2, 1 ) );
  }

  @Test
  public void testCreateFromNode() throws Exception {

    StringBuilder sb = new StringBuilder();

    sb.append( "<data>" ).append( "<columns>" ).append( "<col1 type=\"type1\"/>" ).append( "<col2 type=\"type2\"/>" )
        .append( "<col3 type=\"type2\"/>" ).append( "</columns>" ).append( "<default-value>" ).append( "<row>" )
        .append( "<col1>a</col1>" ).append( "<col2>1</col2>" ).append( "</row>" ).append( "<row>" ).append(
            "<col1>b</col1>" ).append( "<col2>2</col2>" ).append( "</row>" ).append( "<row>" )
        .append( "<col1>c</col1>" ).append( "<col2>3</col2>" ).append( "</row>" ).append( "</default-value>" ).append(
            "</data>" );

    Document doc = DocumentHelper.parseText( sb.toString() );
    Node node = (Node) doc.getRootElement();

    MemoryResultSet data = MemoryResultSet.createFromActionSequenceInputsNode( node );

    assertNotNull( data.getMetaData() );
    assertNotNull( data.getMetaData().getColumnHeaders() );
    assertNull( data.getMetaData().getRowHeaders() );

    assertEquals( 3, data.getMetaData().getColumnCount() );
    assertEquals( 1, data.getMetaData().getColumnHeaders().length );
    assertEquals( 3, data.getMetaData().getColumnHeaders()[0].length );
    assertEquals( "col1", data.getMetaData().getColumnHeaders()[0][0] );
    assertEquals( "col2", data.getMetaData().getColumnHeaders()[0][1] );

    assertEquals( 3, data.getRowCount() );
    assertEquals( "a", data.getValueAt( 0, 0 ) );
    assertEquals( "1", data.getValueAt( 0, 1 ) );
    assertNull( data.getValueAt( 0, 2 ) );
    assertEquals( "b", data.getValueAt( 1, 0 ) );
    assertEquals( "2", data.getValueAt( 1, 1 ) );
    assertNull( data.getValueAt( 1, 2 ) );
    assertEquals( "c", data.getValueAt( 2, 0 ) );
    assertEquals( "3", data.getValueAt( 2, 1 ) );
    assertNull( data.getValueAt( 2, 2 ) );

    assertEquals( "test", MemoryResultSet.getNodeText( null, null, "test" ) );

  }

}
