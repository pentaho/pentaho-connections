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

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * This object stores the names of the columns in a MarshallableResultSet.
 * 
 * @author jamesdixon
 * 
 */
@XmlRootElement
public class MarshallableColumnNames implements Serializable {

  private static final long serialVersionUID = -1224364938886117109L;

  String[] names;

  public MarshallableColumnNames() {
  }

  /**
   * Sets the column names for the result set
   * 
   * @param names
   */
  public void setColumnNames( String[] names ) {
    this.names = names;
  }

  /**
   * Returns the column names for the result set
   * 
   * @return
   */
  public String[] getColumnName() {
    return names;
  }

}
