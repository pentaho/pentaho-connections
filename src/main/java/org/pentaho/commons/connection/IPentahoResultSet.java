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
