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

package org.pentaho.commons.connection.marshal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlRootElement;

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
