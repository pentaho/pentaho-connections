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
