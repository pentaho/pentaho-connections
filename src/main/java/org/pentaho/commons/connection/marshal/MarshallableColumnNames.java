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
 * Copyright (c) 2002-2024 Hitachi Vantara..  All rights reserved.
 */
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
