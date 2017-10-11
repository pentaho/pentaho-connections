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

import java.util.List;

import org.pentaho.commons.connection.AbstractPentahoMetaData;
import org.pentaho.commons.connection.IMultiDimensionalMetaData;
import org.pentaho.commons.connection.MetaDataUtil;

public class MemoryMetaData extends AbstractPentahoMetaData implements IMultiDimensionalMetaData {

  String[] columnTypes;

  protected Object[][] columnHeaders;

  protected Object[][] rowHeaders;

  protected String[] columnNames; // row header names and collapsed version of the column headers

  protected String[] rowHeaderNames; // names of the row header columns

  protected String columnNameFormatStr; // format mask to use to generate the columnNames

  public MemoryMetaData( Object[][] columnHeaders, Object[][] rowHeaders, String columnNameFormatStr,
      String[] columnTypes, String[] columnNames, String[] rowHeaderNames  ) {
    this.columnHeaders = columnHeaders;
    this.rowHeaders = rowHeaders;
    this.columnNameFormatStr = columnNameFormatStr;
    this.columnTypes = columnTypes;
    this.columnNames = columnNames;
    this.rowHeaderNames = rowHeaderNames;
  }

  public MemoryMetaData( MemoryMetaData metaData ) {
    this.columnHeaders = metaData.getColumnHeaders();
    this.rowHeaders = metaData.getRowHeaders();
    this.columnNameFormatStr = metaData.getColumnNameFormat();
    this.columnTypes = metaData.getColumnTypes();
    this.columnNames = metaData.getFlattenedColumnNames();
    this.rowHeaderNames = metaData.getRowHeaderNames();
  }

  @Deprecated
  public MemoryMetaData( Object[][] columnHeaders, Object[][] rowHeaders ) {
    this.columnHeaders = columnHeaders;
    this.rowHeaders = rowHeaders;
  }

  @Deprecated
  @SuppressWarnings( "rawtypes" )
  public MemoryMetaData( List columnHeadersList ) {
    Object[][] tmp = new String[1][columnHeadersList.size()];
    for ( int i = 0; i < columnHeadersList.size(); i++ ) {
      tmp[0][i] = columnHeadersList.get( i );
    }
    this.columnHeaders = tmp;
    this.rowHeaders = null;
  }

  public void setColumnTypes( String[] columnTypes ) {
    this.columnTypes = columnTypes;
  }

  public String[] getColumnTypes() {
    return columnTypes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.connection.IPentahoMetaData#getColumnHeaders()
   */
  public Object[][] getColumnHeaders() {
    return columnHeaders;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.connection.IPentahoMetaData#getRowHeaders()
   */
  public Object[][] getRowHeaders() {
    return rowHeaders;
  }

  public void setRowHeaders( Object[][] rowHeaders ) {
    this.rowHeaders = rowHeaders;
  }

  public String[] getFlattenedColumnNames() {
    if ( columnNames == null ) {
      generateColumnNames();
    }
    return columnNames;
  }

  public void setFlattenedColumnNames( String[] columnNames ) {
    this.columnNames = columnNames;
  }

  public void setRowHeaderNames( String[] rowHeaderNames ) {
    this.rowHeaderNames = rowHeaderNames;
  }

  public String[] getRowHeaderNames() {
    return rowHeaderNames;
  }

  public void setColumnNameFormat( String formatStr ) {
    this.columnNameFormatStr = formatStr;
  }

  public String getColumnNameFormat() {
    return columnNameFormatStr;
  }

  public void generateColumnNames() {
    columnNames = MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );
  }

}
