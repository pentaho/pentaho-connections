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

package org.pentaho.commons.connection;

public class MetaDataUtil {

  /**
   * Generates a set of column names given column headers, row headers (optional), row header names (optional) and a
   * format string for the column headers. The number of column names returned will be equal to the number of row
   * headers in the first row of row headers and the number of columns in the first row of column headers. It is assumed
   * that the row and column headers arrays are not ragged. The row headers can be null. The format string can be null.
   * 
   * @param columnHeaders
   * @param rowHeaders
   * @param rowHeaderNames
   * @param columnNameFormatStr
   * @return
   */
  public static String[] generateColumnNames( Object[][] columnHeaders, Object[][] rowHeaders, String[] rowHeaderNames,
      String columnNameFormatStr ) {
    // look at the row headers
    boolean haveRowHeaderNames = false;
    int rowHeaderCount = 0;
    if ( rowHeaders != null && rowHeaders.length > 0 && rowHeaders[0].length > 0 ) {
      // we have row headers to deal with
      rowHeaderCount = rowHeaders[0].length;
      if ( rowHeaderNames != null && rowHeaderNames.length >= rowHeaders[0].length ) {
        haveRowHeaderNames = true;
      }
    }
    // now look at the column headers
    int columnHeaderCount = 0;
    if ( columnHeaders != null && columnHeaders.length > 0 && columnHeaders[0].length > 0 ) {
      // we have row headers to deal with
      columnHeaderCount = columnHeaders[0].length;
    }
    // we have the total count now
    String[] columnNames = new String[rowHeaderCount + columnHeaderCount];

    // add the row header names
    if ( rowHeaderCount > 0 ) {
      if ( haveRowHeaderNames ) {
        // use the row header names provided
        for ( int idx = 0; idx < rowHeaderCount; idx++ ) {
          columnNames[idx] = rowHeaderNames[idx];
        }
      } else {
        // use a message string to generate a row header name
        for ( int idx = 0; idx < rowHeaderCount; idx++ ) {
          columnNames[idx] =
              Messages.getString( "MetaDataUtil.USER_DEFAULT_ROW_HEADER_NAME", Integer.toString( idx + 1 ) ); //$NON-NLS-1$
        }
      }
    }
    // add the column header names
    if ( columnHeaderCount > 0 ) {
      if ( columnHeaders.length == 1 ) {
        // this is the case of one column header per column so we can use the column header as-is
        for ( int idx = 0; idx < columnHeaderCount; idx++ ) {
          columnNames[idx + rowHeaderCount] = columnHeaders[0][idx].toString();
        }
      } else if ( columnNameFormatStr != null ) {
        // this is the case of one column header per column so we can use the column header as-is
        for ( int idx = 0; idx < columnHeaderCount; idx++ ) {
          String name = columnNameFormatStr;
          for ( int headerNo = 0; headerNo < columnHeaders.length; headerNo++ ) {
            name = name.replace( "{" + headerNo + "}", columnHeaders[headerNo][idx].toString() ); //$NON-NLS-1$//$NON-NLS-2$
          }
          columnNames[idx + rowHeaderCount] = name;
        }
      } else {
        // we have no format string and multiple headers so append the column headers together
        StringBuilder sb;
        // get the separator to use
        String separator = Messages.getString( "MetaDataUtil.USER_DEFAULT_SEPARATOR" ); //$NON-NLS-1$
        for ( int idx = 0; idx < columnHeaderCount; idx++ ) {
          sb = new StringBuilder();
          for ( int headerNo = 0; headerNo < columnHeaders.length; headerNo++ ) {
            if ( headerNo > 0 ) {
              // add the separator
              sb.append( separator );
            }
            sb.append( columnHeaders[headerNo][idx] );
          }
          columnNames[idx + rowHeaderCount] = sb.toString();
        }
      }
    }

    return columnNames;
  }

}
