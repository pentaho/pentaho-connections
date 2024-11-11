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

public interface IMultiDimensionalMetaData {

  /**
   * @return a 2D grid that represents row headers.
   * 
   *         NOTE: 2D data will return null for the row header. Multidimensional data will return N x M where M is the
   *         number of dimensions and N is the number of rows. With the 0 index for M representing the innermost
   *         dimension. This method has been moved here from IPentahoMetaData
   */
  public Object[][] getRowHeaders();

  /**
   * Gets the column names. These names include the names of the row dimensions and flattened column headers
   * 
   * @return
   */
  public String[] getFlattenedColumnNames();

  /**
   * Gets the names of the row dimensions
   * 
   * @return
   */
  public String[] getRowHeaderNames();

  /**
   * Sets the column name format string. The values in the column headers will be inserted into the format template
   * based on their index (0 based).
   * 
   * e.g if the column headers are: 2009 2009 2008 Apr Jan Oct
   * 
   * and the format string is {1}-{0} the column names will end up as: Apr-2009,Jan-2009,Oct-2008
   * 
   * @param formatStr
   */
  public void setColumnNameFormat( String formatStr );

}
