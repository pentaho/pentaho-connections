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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.pentaho.commons.connection.IPentahoDataTypes;

/**
 * This class stores a list of the column types for a serializable result set. These types are used to provide metadata
 * about the rows of data stored in a MarshallableResultSet object
 * 
 * @author jamesdixon
 * 
 */
@XmlRootElement
public class MarshallableColumnTypes implements Serializable {

  private static final long serialVersionUID = -5961847338607421411L;

  private static Map<String, String> typeMap = new HashMap<String, String>();

  String[] types;

  static {
    typeMap.put( String.class.getName(), IPentahoDataTypes.TYPE_STRING );
    typeMap.put( Double.class.getName(), IPentahoDataTypes.TYPE_DOUBLE );
    typeMap.put( Float.class.getName(), IPentahoDataTypes.TYPE_FLOAT );
    typeMap.put( Integer.class.getName(), IPentahoDataTypes.TYPE_INT );
    typeMap.put( BigDecimal.class.getName(), IPentahoDataTypes.TYPE_DECIMAL );
    typeMap.put( Date.class.getName(), IPentahoDataTypes.TYPE_DATE );
    typeMap.put( Long.class.getName(), IPentahoDataTypes.TYPE_LONG );
    typeMap.put( Boolean.class.getName(), IPentahoDataTypes.TYPE_BOOLEAN );
  }

  public static String getDataType( String javaType ) {
    return typeMap.get( javaType );
  }

  public MarshallableColumnTypes() {
  }

  /**
   * Sets the columns types. See org.pentaho.commons.connection.IPentahoDataTypes for the types supported.
   * 
   * @param types
   */
  public void setColumnType( String[] types ) {
    this.types = types;
  }

  /**
   * Returns an array of the column types. See org.pentaho.commons.connection.IPentahoDataTypes for the types supported.
   * 
   * @return
   */
  public String[] getColumnType() {
    return types;
  }

}
