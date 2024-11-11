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

/**
 * Defines the methods that must be supported for obtaining metadata about a source of data.
 * 
 * @author wseyler
 */
public interface IPentahoMetaData {

  /**
   * @return a 2D grid that represents column headers.
   * 
   *         NOTE: 2D data will contain a column header that is 1 x N where N is the number of columns in the data.
   *         Multidimensional data will return N x M where N is the number of dimensions and M is the number of columns.
   *         With the 0 index for N representing the innermost dimension.
   */
  public Object[][] getColumnHeaders();

  /**
   * @return a 2D grid that represents row headers.
   * 
   *         NOTE: 2D data will return null for the row header. Multidimensional data will return N x M where M is the
   *         number of dimensions and N is the number of rows. With the 0 index for M representing the innermost
   *         dimension. This method has been moved to IMultiDimensionalMetaData
   */
  @Deprecated
  public Object[][] getRowHeaders();

  /**
   * Gets the 0-based column number for a specified column header value. If the column header cannot be found -1 is
   * returned. If there is more than 1 column header dimentsions -1 is returned.
   * 
   * @param value
   *          The column header value to search for.
   * @return The 0-based index of the column
   * @throws Exception
   */
  public int getColumnIndex( String value );

  /**
   * Gets the 0-based column number for a set of column header values. If the column header cannot be found -1 is
   * returned. If the number of column header dimensions does not match the number of values provided -1 is returned.
   * 
   * @param value
   *          The column header value to search for.
   * @return The 0-based index of the column
   */
  public int getColumnIndex( String[] values );

  /**
   * Gets the 0-based row number for a specified row header value. If the row header cannot be found -1 is returned. If
   * there is more than 1 row header dimentsions -1 is returned.
   * 
   * @param value
   *          The row header value to search for.
   * @return The 0-based index of the row
   * @throws Exception
   */
  public int getRowIndex( String value );

  /**
   * Gets the 0-based row number for a set of row header values. If the row header cannot be found -1 is returned. If
   * the number of row header dimensions does not match the number of values provided -1 is returned.
   * 
   * @param value
   *          The row header value to search for.
   * @return The 0-based index of the row
   */
  public int getRowIndex( String[] values );

  /**
   * @return Count of columns returned.
   */
  public int getColumnCount();

  /**
   * get an attribute from the metadata
   * 
   * @param rowNo
   *          the row number
   * @param columnNo
   *          the column number
   * @param attributeName
   *          the attribute name
   * @return the attribute
   */
  public Object getAttribute( int rowNo, int columnNo, String attributeName );
}
