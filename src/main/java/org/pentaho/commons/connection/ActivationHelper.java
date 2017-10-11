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
package org.pentaho.commons.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

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
