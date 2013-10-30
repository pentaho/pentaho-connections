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

import javax.xml.bind.annotation.XmlRootElement;

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
