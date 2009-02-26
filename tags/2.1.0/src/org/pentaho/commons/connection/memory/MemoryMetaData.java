/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
 *
 * @created Sep 7, 2005 
 * @author James Dixon
 */

package org.pentaho.commons.connection.memory;

import java.util.List;

import org.pentaho.commons.connection.AbstractPentahoMetaData;

public class MemoryMetaData extends AbstractPentahoMetaData {

  String[] columnTypes;

  protected Object[][] columnHeaders;

  protected Object[][] rowHeaders;

  public MemoryMetaData(Object[][] columnHeaders, Object[][] rowHeaders) {
    this.columnHeaders = columnHeaders;
    this.rowHeaders = rowHeaders;
  }

  public MemoryMetaData(List columnHeadersList) {
    Object tmp[][] = new String[1][columnHeadersList.size()];
    for (int i = 0; i < columnHeadersList.size(); i++) {
      tmp[0][i] = columnHeadersList.get(i);
    }
    this.columnHeaders = tmp;
    this.rowHeaders = null;
  }

  public void setColumnTypes(String columnTypes[]) {
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

}
