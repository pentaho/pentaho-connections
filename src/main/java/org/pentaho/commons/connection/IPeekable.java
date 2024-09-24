/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2028-08-13
 ******************************************************************************/
package org.pentaho.commons.connection;

public interface IPeekable {

  /**
   * Peeks into a result set object to get a preview of the next row of data. This is an optional interface that
   * IPentahoResultSet objects can implement
   * 
   * @return
   */
  public Object[] peek();

}
