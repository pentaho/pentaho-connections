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

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DataUtilities implements IPentahoDataTypes {
  public DataUtilities() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Pivots a 2D array such that rows become columns and columns become rows. The source array is not modified.
   * 
   * @param source
   *          2D array to pivot
   * @return a 2D array that represents the pivoted source
   * 
   *         ie source: |Data0,0 |Data0,1 |Data0,2 |Data 0,3 | |Data1,0 |Data1,1 |Data1,2 |Data 1,3 | |Data2,0 |Data2,1
   *         |Data2,2 |Data 2,3 | |Data3,0 |Data3,1 |Data3,2 |Data 3,3 | |Data4,0 |Data4,1 |Data4,2 |Data 4,3 | |Data5,0
   *         |Data5,1 |Data5,2 |Data 5,3 |
   * 
   *         becomes: |Data0,0 |Data1,0 |Data2,0 |Data3,0 |Data4,0 |Data5,0 | |Data0,1 |Data1,1 |Data2,1 |Data3,1
   *         |Data4,1 |Data5,1 | |Data0,2 |Data1,2 |Data2,2 |Data3,2 |Data4,2 |Data5,2 | |Data0,3 |Data1,3 |Data2,3
   *         |Data3,3 |Data4,3 |Data5,3 |
   */
  public static Object[][] pivotDimensions( Object[][] source ) {
    Object[][] result = null;
    if ( source.length > 0 ) {
      result = new Object[source[0].length][source.length];
      for ( int row = 0; row < source.length; row++ ) {
        for ( int column = 0; column < source[row].length; column++ ) {
          result[column][row] = source[row][column];
        }
      }
    } else {
      result = source;
    }
    return result;
  }

  /**
   * Selects rows from a 2d array and returns a 2d array that contains only those rows
   * 
   * @param data
   *          2D data to filter
   * @param rowsToInclude
   *          Interger array of rows to include in results
   * @return 2D array of data filtered by rows
   */
  public static Object[][] filterDataByRows( Object[][] data, Integer[] rowsToInclude ) {
    if ( rowsToInclude == null ) { // none to select so just return the
      // data
      return data;
    }
    Object[][] result = new Object[rowsToInclude.length][data[0].length];
    for ( int row = 0; row < rowsToInclude.length; row++ ) {
      Object[] rowHeaderRow = data[rowsToInclude[row].intValue()];
      for ( int column = 0; column < rowHeaderRow.length; column++ ) {
        result[row][column] = rowHeaderRow[column];
      }
    }
    return result;
  }

  /**
   * Selects columns from a 2d array and returns a 2d array that contains only those columns
   * 
   * @param data
   *          2D data to filter
   * @param columnsToInclude
   *          Integer array of rows to include in results
   * @return 2D array of data filtered by columns
   */
  public static Object[][] filterDataByColumns( Object[][] data, Integer[] columnsToInclude ) {
    if ( columnsToInclude == null ) {
      return data;
    }
    Object[][] result = new Object[data.length][columnsToInclude.length];
    for ( int column = 0; column < columnsToInclude.length; column++ ) {
      for ( int row = 0; row < data.length; row++ ) {
        result[row][column] = data[row][columnsToInclude[column].intValue()];
      }
    }
    return result;
  }

  /**
   * Filters the data such that only the rowsToInclude and columnsToInclude are in the dataset
   * 
   * @param data
   *          - 2D data to filter
   * @param rowsToInclude
   *          - Integer array of row numbers to include a null value indicates all rows
   * @param columnsToInclude
   *          Integer array of column numbers to include a null values indicates all columns
   * @return - 2D array of only those rows and columns specified
   */
  public static Object[][] filterData( Object[][] data, Integer[] rowsToInclude, Integer[] columnsToInclude ) {
    Object[][] result = filterDataByColumns( data, columnsToInclude );
    result = filterDataByRows( result, rowsToInclude );
    return result;
  }

  /**
   * Get an XML representation of the resultset
   * 
   * @return String containing XML which represents the data (eg for use with xquery)
   */
  public static String getXMLString( IPentahoResultSet resultSet ) {
    String xmlResultString = null;
    Document document = DOMDocumentFactory.getInstance().createDocument();
    org.dom4j.Element resultSetNode = document.addElement( "result-set" ); //$NON-NLS-1$
    Object[] colHeaders = resultSet.getMetaData().getColumnHeaders()[0];
    String metaDataStr = ""; //$NON-NLS-1$
    Object[] firstDataRow = resultSet.getDataRow( 0 );
    for ( int i = 0; i < firstDataRow.length; i++ ) {
      metaDataStr += firstDataRow[i].getClass().getName() + ( i == firstDataRow.length - 1 ? "" : "," ); //$NON-NLS-1$//$NON-NLS-2$
    }
    resultSetNode.addComment( metaDataStr );
    int rowCount = resultSet.getRowCount();
    for ( int i = 0; i < rowCount; i++ ) {
      Object[] row = resultSet.getDataRow( i );
      org.dom4j.Element rowNode = resultSetNode.addElement( "row" ); //$NON-NLS-1$
      for ( int j = 0; j < row.length; j++ ) {
        String column = colHeaders[j].toString();
        String value = row[j] != null ? row[j].toString() : ""; //$NON-NLS-1$
        if ( row[j] instanceof Timestamp || row[j] instanceof Date ) {
          value = String.valueOf( ( (Timestamp) row[j] ).getTime() );
        }
        org.dom4j.Element dataNode = rowNode.addElement( column );
        dataNode.setText( value );
      }
    }
    OutputFormat format = OutputFormat.createPrettyPrint();
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    try {
      XMLWriter writer = new XMLWriter( outStream, format );
      writer.write( document );
      xmlResultString = outStream.toString();
    } catch ( Exception e ) {
      // try to return the xml unformatted
      xmlResultString = document.asXML();
    }
    return xmlResultString;
  }

  /**
   * Converts an array of objects to a list of Numbers. If the objects are Number types, they will simply be cast,
   * otherwise this method will attempt to convert object.toString() to a number using the provided number formats.
   * 
   * @param rowData
   *          the input object array
   * @param formats
   *          list of {@link NumberFormat}'s used to convert the objects to {@link Number}'s
   * @return list of Numbers that represent the original rowData
   */
  public static List<Number> toNumbers( Object[] rowData, NumberFormat... formats ) {
    List<Number> numberList = new ArrayList<Number>();
    for ( int i = 0; i < rowData.length; i++ ) {
      if ( rowData[i] == null ) {
        throw new IllegalArgumentException( Messages.getString( "DataUtilities.ERROR_0001_CANNOT_COVERT_NULL_DATA" ) ); //$NON-NLS-1$
      }
      if ( Number.class.isAssignableFrom( rowData[i].getClass() ) ) {
        numberList.add( (Number) rowData[i] );
      } else {
        // try to convert the data to a Number using the number formats
        for ( int j = 0; j < formats.length; j++ ) {
          Number num;
          try {
            num = formats[j].parse( rowData[i].toString() );
          } catch ( ParseException e ) {
            if ( j < formats.length - 1 ) {
              continue;
            } else {
              throw new IllegalArgumentException( e );
            }
          }
          numberList.add( num );
          break;
        }
      }
    }
    return numberList;
  }

  /**
   * Convenience wrapper for {@link DataUtilities#toNumbers(Object[], NumberFormat[])}
   * 
   * @param data
   *          to convert to a number
   * @param formats
   * @return a number
   */
  public static Number toNumber( Object data, NumberFormat... formats ) {
    return toNumbers( new Object[] { data }, formats ).get( 0 );
  }
}
