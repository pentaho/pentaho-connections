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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IPentahoStreamSource {

  public abstract String getContentType();

  public abstract InputStream getInputStream() throws IOException;

  public abstract String getName();

  public abstract OutputStream getOutputStream() throws IOException;
}
