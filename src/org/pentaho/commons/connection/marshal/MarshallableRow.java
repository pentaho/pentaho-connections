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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.pentaho.commons.connection.IPentahoDataTypes;

/**
 * Stores a row of data for a MarshallableResultSet.
 * 
 * @author jamesdixon
 * 
 */
@XmlRootElement
public class MarshallableRow implements Serializable {

  private static final long serialVersionUID = 3397670591600236990L;

  String[] row;

  public MarshallableRow() {
  }

  /**
   * Sets the cell values to be stored. This object does not enforce that all rows in the result set have the same
   * number of cells but it is assumed that this is the case.
   * 
   * @param row
   */
  public void setCell( Object[] row ) {
    this.row = new String[row.length];
    for ( int colNo = 0; colNo < row.length; colNo++ ) {
      if ( row[colNo] == null ) {
        this.row[colNo] = null;
      } else if ( row[colNo] instanceof Date ) {
        SimpleDateFormat fmt = new SimpleDateFormat( IPentahoDataTypes.DATE_FORMAT );
        this.row[colNo] = fmt.format( row[colNo] );
        // insert a 'T' to make this W3C compliant
        this.row[colNo] = this.row[colNo].replace( '.', 'T' );
      } else {
        this.row[colNo] = row[colNo].toString();
      }
    }
  }

  /**
   * Gets the cells for this row.
   * 
   * @return
   */
  public String[] getCell() {
    return row;
  }

}
