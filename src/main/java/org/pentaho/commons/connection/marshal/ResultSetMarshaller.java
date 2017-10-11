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
package org.pentaho.commons.connection.marshal;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.pentaho.commons.connection.IPentahoDataTypes;
import org.pentaho.commons.connection.IPentahoResultSet;
import org.pentaho.commons.connection.memory.MemoryMetaData;
import org.pentaho.commons.connection.memory.MemoryResultSet;

/**
 * A utility class for marshalling IPentahoResultSet objects into and out of a serializable form (MarshallableResultSet)
 * 
 * @author jamesdixon
 * 
 */
public class ResultSetMarshaller {

  /**
   * Converts an IPentahoResultSet into a MarshallableResultSet
   * 
   * @param results
   * @return
   */
  public static MarshallableResultSet fromResultSet( IPentahoResultSet results ) {

    MarshallableResultSet data = new MarshallableResultSet();
    data.setResultSet( results );
    return data;

  }

  /**
   * Converts a MarshallableResultSet object into an IPentahoResultSet. A MemoryResultSet object is used as the
   * implementation.
   * 
   * @param data
   * @return
   */
  public static IPentahoResultSet toResultSet( MarshallableResultSet data ) {

    MarshallableColumnNames columnNames = data.getColumnNames();
    MarshallableColumnTypes columnTypes = data.getColumnTypes();
    MarshallableRow[] rows = data.getRows();

    String[] names = columnNames.getColumnName();
    String[] types = columnTypes.getColumnType();

    MemoryMetaData metadata = new MemoryMetaData( new Object[][] { names }, null );
    metadata.setColumnTypes( types );
    MemoryResultSet result = new MemoryResultSet( metadata );
    int columnCount = names.length;

    for ( int i = 0; i < rows.length; i++ ) {
      String[] rowAsStrings = rows[i].getCell();
      Object[] row = new Object[columnCount];
      // TODO - parsing to get original types back
      for ( int colNo = 0; colNo < columnCount; colNo++ ) {
        row[colNo] = convertCell( rowAsStrings[colNo], types[colNo] );
      }
      result.addRow( row );
    }

    return result;
  }

  /**
   * Converts a single cell value from a string into its original object type
   * 
   * @param value
   * @param type
   * @return
   */
  public static Object convertCell( String value, String type ) {
    if ( IPentahoDataTypes.TYPE_STRING.equals( type ) ) {
      return value;
    }
    if ( value == null || "".equals( value ) ) { //$NON-NLS-1$
      return null;
    } else if ( IPentahoDataTypes.TYPE_INT.equals( type ) ) {
      return Integer.parseInt( value );
    } else if ( IPentahoDataTypes.TYPE_LONG.equals( type ) ) {
      return Long.parseLong( value );
    } else if ( IPentahoDataTypes.TYPE_DECIMAL.equals( type ) ) {
      return new BigDecimal( value );
    } else if ( IPentahoDataTypes.TYPE_FLOAT.equals( type ) ) {
      return Float.parseFloat( value );
    } else if ( IPentahoDataTypes.TYPE_DOUBLE.equals( type ) ) {
      return Double.parseDouble( value );
    } else if ( IPentahoDataTypes.TYPE_BOOLEAN.equals( type ) ) {
      return Boolean.parseBoolean( value );
    } else if ( IPentahoDataTypes.TYPE_DATE.equals( type ) ) {
      SimpleDateFormat fmt = new SimpleDateFormat( IPentahoDataTypes.DATE_FORMAT );
      try {
        return fmt.parse( value.replace( 'T', '.' ) );
      } catch ( ParseException e ) {
        // TODO log this
        // this is kind of bogus - caller is likely expecting a date, not string. But this would be a behavior change to
        // error out

        return value;
      }
    }
    // TODO calendars
    // we could not convert it
    return value;
  }
}
