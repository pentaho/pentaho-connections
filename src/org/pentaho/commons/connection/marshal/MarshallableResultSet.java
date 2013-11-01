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

package org.pentaho.commons.connection.marshal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pentaho.commons.connection.IPentahoDataTypes;
import org.pentaho.commons.connection.IPentahoMetaData;
import org.pentaho.commons.connection.IPentahoResultSet;

/**
 * A serializable representation of an IPentahoResultSet. This class can be populated from an IPentahoResultSet object
 * and serialized. A MemoryResultSet object can be created from one of these using WSResultSetUtil
 * 
 * @author jamesdixon
 * 
 */
@XmlRootElement
public class MarshallableResultSet implements Serializable {

  private static final long serialVersionUID = 3272001968807944367L;

  private MarshallableRow[] rows;

  private MarshallableColumnTypes columnTypes;

  private MarshallableColumnNames columnNames;

  private int numColumnHeaderSets = 0;

  private int numRowHeaderSets = 0;

  public MarshallableResultSet() {
  }

  /**
   * Sets the result set to be converted
   * 
   * @param results
   */
  public void setResultSet( IPentahoResultSet results ) {

    int colCount = results.getColumnCount();

    // create the metadata
    IPentahoMetaData metadata = results.getMetaData();
    String[] tmp = new String[colCount];
    Object[][] colHeaders = metadata.getColumnHeaders();
    for ( int colNo = 0; colNo < colHeaders[0].length; colNo++ ) {
      tmp[colNo] = colHeaders[0][colNo].toString();
    }
    columnNames = new MarshallableColumnNames();
    columnNames.setColumnNames( tmp );

    // look at the first row to
    columnTypes = new MarshallableColumnTypes();

    // create the 2D array of data
    List<MarshallableRow> rowList = new ArrayList<MarshallableRow>();

    int unknownTypes = colCount;
    String[] tmpTypes = new String[colCount];
    Object[] rowObjects = results.next();
    while ( rowObjects != null ) {
      MarshallableRow row = new MarshallableRow();
      row.setCell( rowObjects );
      rowList.add( row );
      // see if we can resolve any unknown types using the data on this row
      if ( unknownTypes > 0 ) {
        for ( int colNo = 0; colNo < rowObjects.length; colNo++ ) {
          if ( tmpTypes[colNo] == null && rowObjects[colNo] != null ) {
            tmpTypes[colNo] = MarshallableColumnTypes.getDataType( rowObjects[colNo].getClass().getName() );
            unknownTypes--;
          }
        }
      }
      rowObjects = results.next();
    }
    rows = new MarshallableRow[rowList.size()];
    rowList.toArray( rows );
    // pick string type for any column that had nulls in every row
    if ( unknownTypes > 0 ) {
      for ( int colNo = 0; colNo < tmpTypes.length; colNo++ ) {
        if ( tmpTypes[colNo] == null ) {
          tmpTypes[colNo] = IPentahoDataTypes.TYPE_STRING;
        }
      }
    }
    columnTypes.setColumnType( tmpTypes );

  }

  /**
   * Returns the collection of rows for this result set
   * 
   * @return
   */
  public MarshallableRow[] getRows() {
    return rows;
  }

  /**
   * Returns the names of the columns for this result set
   * 
   * @return
   */
  public MarshallableColumnNames getColumnNames() {
    return columnNames;
  }

  /**
   * Returns the name of the types for this result set
   * 
   * @return
   */
  public MarshallableColumnTypes getColumnTypes() {
    return columnTypes;
  }

  public int getNumColumnHeaderSets() {
    return numColumnHeaderSets;
  }

  public void setNumColumnHeaderSets( int numColumnHeaderSets ) {
    this.numColumnHeaderSets = numColumnHeaderSets;
  }

  public int getNumRowHeaderSets() {
    return numRowHeaderSets;
  }

  public void setNumRowHeaderSets( int numRowHeaderSets ) {
    this.numRowHeaderSets = numRowHeaderSets;
  }

}
