/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2028-08-13
 ******************************************************************************/
package org.pentaho.commons.connection;

/**
 * Defines a set of result data, typically with definable rows and columns. Supports multi-dimensional data sets.
 * 
 * @author wseyler
 * @see IDisposable
 * 
 */
public interface IPentahoResultSet extends IDisposable {
  /**
   * @return the Metadata object that resulted from the most recent query.
   */
  IPentahoMetaData getMetaData();

  /**
   * @return an object array that represents the data in each column of the next row.
   */
  Object[] next();

  /**
   * Moves the cursor to before the first row
   * 
   */
  void beforeFirst();

  /**
   * Close the query
   */
  void close();

  /**
   * Close the connection used by this result set
   * 
   */
  void closeConnection();

  /**
   * Indicates whether the result set is scrollable
   * 
   * @return true if the resultset can be scrolled through.
   */
  public boolean isScrollable();

  /**
   * Returns the value of the specified row and the specified column from within the resultset.
   * 
   * @param row
   *          the row index.
   * @param column
   *          the column index.
   * @return the value.
   */
  public Object getValueAt( int row, int column );

  /**
   * Get a rowCount from the resultset.
   * 
   * @return the row count.
   */
  public int getRowCount();

  /**
   * Returns the rowCount from the result set.
   * 
   * @return
   */
  public int getColumnCount();

  /**
   * Returns a memory copy of the results
   * 
   * @return memory copy of the results
   */
  public IPentahoResultSet memoryCopy();

  /**
   * Get a column of data.
   * 
   * @param column
   *          the zero based column number
   * @return array represeting a column of data. 0th element is the first column
   */
  public Object[] getDataColumn( int column );

  /**
   * Get a specified row of data
   * 
   * @param row
   *          the zero base row number
   * @return array representing a row of data. 0th element is the top row
   */
  public Object[] getDataRow( int row );
}
