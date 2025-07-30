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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;

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
