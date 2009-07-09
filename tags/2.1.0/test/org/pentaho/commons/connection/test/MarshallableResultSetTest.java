package org.pentaho.commons.connection.test;

import java.math.BigDecimal;
import java.util.Date;

import org.pentaho.commons.connection.IPentahoDataTypes;
import org.pentaho.commons.connection.IPentahoMetaData;
import org.pentaho.commons.connection.IPentahoResultSet;
import org.pentaho.commons.connection.marshal.MarshallableColumnNames;
import org.pentaho.commons.connection.marshal.MarshallableColumnTypes;
import org.pentaho.commons.connection.marshal.MarshallableResultSet;
import org.pentaho.commons.connection.marshal.MarshallableRow;
import org.pentaho.commons.connection.marshal.ResultSetMarshaller;
import org.pentaho.commons.connection.memory.MemoryMetaData;
import org.pentaho.commons.connection.memory.MemoryResultSet;

import junit.framework.TestCase;

@SuppressWarnings({"all"})
public class MarshallableResultSetTest extends TestCase {

  public void testWSResultSet1() {
    
    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "col1", "col2" } }, null );
    
    metadata.setColumnTypes(new String[] { "java.lang.String", "java.lang.int" } );
    
    MemoryResultSet data = new MemoryResultSet( metadata );
    
    data.addRow( new Object[] {"a", new Integer(1) } );
    data.addRow( new Object[] {"b", new Integer(2) } );
    data.addRow( new Object[] {"c", new Integer(3) } );

    MarshallableResultSet result = new MarshallableResultSet( );
    result.setResultSet(data);
    validate( result );

  }

  public void testUtil() {
    
    // this should not throw errors
    new ResultSetMarshaller();
    
    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "mystr", "myint", "mylong", "mybool", "mydecimal", "mydate", "myfloat", "mydouble", "myunknown" } }, null );
    MemoryResultSet data = new MemoryResultSet( metadata );
    
    data.addRow( new Object[] {"apple", 1, new Long(Integer.MAX_VALUE + 1), true, new BigDecimal( "10.99" ), new Date( 98, 1, 14), null, new Double( 123.45 ), null } );
    data.addRow( new Object[] {"banana", 20, new Long(Integer.MIN_VALUE - 1), false, new BigDecimal( "1000000000000.99" ), new Date( 109, 1, 4), new Float( 99.9 ), null, null } );
    data.addRow( new Object[] {"", 333, (long) 0, true, null, null, new Float( 99.9 ), new Double( 123.45 ), null } );

    MarshallableResultSet wsData = ResultSetMarshaller.fromResultSet(data);
    
    IPentahoResultSet result = ResultSetMarshaller.toResultSet( wsData );
    
    assertEquals( 9, result.getColumnCount() );
    assertEquals( 3, result.getRowCount() );
    
    IPentahoMetaData metadata2 = result.getMetaData();
    
    assertEquals( "mystr", metadata2.getColumnHeaders()[0][0] );
    assertEquals( "myint", metadata2.getColumnHeaders()[0][1] );
    assertEquals( "mylong", metadata2.getColumnHeaders()[0][2] );
    assertEquals( "mybool", metadata2.getColumnHeaders()[0][3] );
    assertEquals( "mydecimal", metadata2.getColumnHeaders()[0][4] );
    assertEquals( "mydate", metadata2.getColumnHeaders()[0][5] );
    assertEquals( "myfloat", metadata2.getColumnHeaders()[0][6] );
    assertEquals( "mydouble", metadata2.getColumnHeaders()[0][7] );
    assertEquals( "myunknown", metadata2.getColumnHeaders()[0][8] );

    Object row[] = result.next();
    assertEquals( "apple", row[0] );
    assertEquals( 1, row[1] );
    assertEquals( new Long(Integer.MAX_VALUE + 1), row[2] );
    assertEquals( true, row[3] );
    assertEquals( new BigDecimal( "10.99" ), row[4] );
    assertEquals( new Date( 98, 1, 14), row[5] );
    assertEquals( null, row[6] );
    assertEquals( "123.45", row[7].toString().substring(0, 6) );
    assertEquals( null, row[8] );
    assertTrue( row[0] instanceof String );
    assertTrue( row[1] instanceof Integer );
    assertTrue( row[2] instanceof Long );
    assertTrue( row[3] instanceof Boolean );
    assertTrue( row[4] instanceof BigDecimal );
    assertTrue( row[5] instanceof Date );
    assertTrue( row[7] instanceof Double );    
    
    row = result.next();
    assertEquals( "banana", row[0] );
    assertEquals( 20, row[1] );
    assertEquals( new Long(Integer.MIN_VALUE - 1), row[2] );
    assertEquals( false, row[3] );
    assertEquals( new BigDecimal( "1000000000000.99" ), row[4] );
    assertEquals( new Date( 109, 1, 4), row[5] );
    assertEquals( "99.9", row[6].toString().substring(0, 4) );
    assertEquals( null, row[7] );
    assertEquals( null, row[8] );
    assertTrue( row[0] instanceof String );
    assertTrue( row[1] instanceof Integer );
    assertTrue( row[2] instanceof Long );
    assertTrue( row[3] instanceof Boolean );
    assertTrue( row[4] instanceof BigDecimal );
    assertTrue( row[5] instanceof Date );
    assertTrue( row[6] instanceof Float );
    
    row = result.next();
    assertEquals( "", row[0] );
    assertEquals( 333, row[1] );
    assertEquals( new Long(0), row[2] );
    assertEquals( true, row[3] );
    assertEquals( null, row[4] );
    assertEquals( null, row[5] );
    assertEquals( "99.9", row[6].toString().substring(0, 4) );
    assertEquals( "123.45", row[7].toString().substring(0, 6) );
    assertEquals( null, row[8] );
    assertTrue( row[0] instanceof String );
    assertTrue( row[1] instanceof Integer );
    assertTrue( row[2] instanceof Long );
    assertTrue( row[3] instanceof Boolean );
    assertTrue( row[6] instanceof Float );
    assertTrue( row[7] instanceof Double );    

  }
  
  public void testNoRows() {
    
    MemoryMetaData metadata = new MemoryMetaData( new String[][] { { "mystr", "myint", "mylong", "mybool", "mydecimal", "mydate", "myfloat", "mydouble" } }, null );
    MemoryResultSet data = new MemoryResultSet( metadata );
    
    MarshallableResultSet wsData = ResultSetMarshaller.fromResultSet(data);
    
    IPentahoResultSet result = ResultSetMarshaller.toResultSet( wsData );
    
    assertEquals( 8, result.getColumnCount() );
    assertEquals( 0, result.getRowCount() );
    
  }
  
  public void testConvertCell() {
    
    // test an unknown type
    Object obj = ResultSetMarshaller.convertCell( "my custom type", "custom" );
    assertTrue( obj instanceof String );
    assertEquals( "my custom type", obj );
    
    // test a bad date
    obj = ResultSetMarshaller.convertCell( "bad date", IPentahoDataTypes.TYPE_DATE );
    assertTrue( obj instanceof String );
    assertEquals( "bad date", obj );
    
    // test a null object
    obj = ResultSetMarshaller.convertCell( null, IPentahoDataTypes.TYPE_DATE );
    assertNull( obj );
    
    // test an empty object
    obj = ResultSetMarshaller.convertCell( "", IPentahoDataTypes.TYPE_DATE );
    assertNull( obj );
    
    // test an empty string
    obj = ResultSetMarshaller.convertCell( "", IPentahoDataTypes.TYPE_STRING );
    assertNotNull( obj );
    
  }
  
  private void validate( MarshallableResultSet result ) {
    
    MarshallableColumnNames colNames = result.getColumnNames();
    assertNotNull( colNames );
    String cols[] = colNames.getColumnName();
    assertNotNull( cols );
    assertEquals( 2, cols.length );
    assertEquals( "col1", cols[0] );
    assertEquals( "col2", cols[1] );

    MarshallableColumnTypes colTypes = result.getColumnTypes();
    assertNotNull( colTypes );
    String types[] = colTypes.getColumnType();
    assertNotNull( types );
    assertEquals( 2, types.length );
    assertEquals( IPentahoDataTypes.TYPE_STRING, types[0] );
    assertEquals( IPentahoDataTypes.TYPE_INT, types[1] );
    
    MarshallableRow rows[] = result.getRows();
    assertNotNull( rows );
    assertEquals( 3, rows.length );
    
    MarshallableRow row = rows[0];
    assertNotNull( row );
    String cells[] = row.getCell();
    assertEquals( 2, cells.length );
    assertEquals( "a", cells[0] );
    assertEquals( "1", cells[1] );

    row = rows[1];
    assertNotNull( row );
    cells = row.getCell();
    assertEquals( 2, cells.length );
    assertEquals( "b", cells[0] );
    assertEquals( "2", cells[1] );

    row = rows[2];
    assertNotNull( row );
    cells = row.getCell();
    assertEquals( 2, cells.length );
    assertEquals( "c", cells[0] );
    assertEquals( "3", cells[1] );

    
  }
  
}
