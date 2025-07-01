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

import jakarta.activation.DataSource;

public class ActivationHelper {

  public static final class DatasourceWrapper implements IPentahoStreamSource {
    private DataSource dataSource;

    public DatasourceWrapper( DataSource ds ) {
      super();
      assert ( ds != null );
      this.dataSource = ds;
    }

    public String getContentType() {
      return dataSource.getContentType();
    }

    public InputStream getInputStream() throws IOException {
      return dataSource.getInputStream();
    }

    public String getName() {
      return dataSource.getName();
    }

    public OutputStream getOutputStream() throws IOException {
      return dataSource.getOutputStream();
    }
  }

  public static final class PentahoStreamSourceWrapper implements DataSource {
    private IPentahoStreamSource streamSource;

    public PentahoStreamSourceWrapper( IPentahoStreamSource ss ) {
      super();
      assert ss != null;
      this.streamSource = ss;
    }

    public String getContentType() {
      return streamSource.getContentType();
    }

    public InputStream getInputStream() throws IOException {
      return streamSource.getInputStream();
    }

    public String getName() {
      return streamSource.getName();
    }

    public OutputStream getOutputStream() throws IOException {
      return streamSource.getOutputStream();
    }
  }

}
