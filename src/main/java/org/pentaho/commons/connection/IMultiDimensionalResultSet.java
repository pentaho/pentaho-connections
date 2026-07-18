/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 - 2026 by Pentaho Canada Inc. : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2030-06-15
 ******************************************************************************/


package org.pentaho.commons.connection;

public interface IMultiDimensionalResultSet {

  /**
   * Gets the next flattened row. This includes the row headers (if any)
   * 
   * @return
   */
  public Object[] nextFlattened();

  /**
   * Peeks at the next flattened row. This includes the row headers (if any)
   * 
   * @return
   */
  public Object[] peekFlattened();

}
