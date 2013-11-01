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
