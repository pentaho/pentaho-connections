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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.activation.DataSource;

public class InputStreamDataSource implements DataSource {
  String name;

  InputStream inputStream;

  public InputStreamDataSource( final String name, final InputStream inInputStream ) {
    this.name = name;
    inputStream = inInputStream;
  }

  public String getContentType() {
    return "application/octet-stream"; //$NON-NLS-1$
  }

  public InputStream getInputStream() throws IOException {
    inputStream.reset();
    return inputStream;
  }

  public String getName() {
    return name;
  }

  public OutputStream getOutputStream() throws IOException {
    return ( new ByteArrayOutputStream() );
  }

}
