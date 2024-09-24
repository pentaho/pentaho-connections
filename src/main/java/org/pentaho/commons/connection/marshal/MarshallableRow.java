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
