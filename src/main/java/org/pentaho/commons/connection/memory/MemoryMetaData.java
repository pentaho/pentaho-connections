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
