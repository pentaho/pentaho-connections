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

package org.pentaho.commons.connection;

public interface ILimitableConnection {

  /**
   * The timeout if supported by the underlying connection
   * 
   * @param timeout
   *          in seconds
   */
  void setQueryTimeout( int timeout );

  /**
   * Max rows returned by query if supported by the underlying connection
   * 
   * @param timeout
   *          in seconds
   */
  void setMaxRows( int maxRows );

}
