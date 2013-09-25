/*
 * Copyright 2002 - 2013 Pentaho Corporation.  All rights reserved.
 * 
 * This software was developed by Pentaho Corporation and is provided under the terms
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. TThe Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to
 * the license for the specific language governing your rights and limitations.
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

  public MemoryMetaData( Object[][] columnHeaders, Object[][] rowHeaders ) {
    this.columnHeaders = columnHeaders;
    this.rowHeaders = rowHeaders;
  }

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

  public void generateColumnNames() {
    columnNames = MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );
  }
}
