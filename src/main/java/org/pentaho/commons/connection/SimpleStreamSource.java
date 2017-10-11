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

/**
 * A simple POJO implementation of IPentahoStreamSource
 * 
 * @author jamesdixon
 * 
 */
public class SimpleStreamSource implements IPentahoStreamSource {

  private String name;

  private String contentType;

  private InputStream inputStream;

  private OutputStream outputStream;

  public SimpleStreamSource( String name, String contentType, InputStream inputStream, OutputStream outputStream ) {
    this.name = name;
    this.contentType = contentType;
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  public String getContentType() {
    return contentType;
  }

  public InputStream getInputStream() throws IOException {
    return inputStream;
  }

  public String getName() {
    return name;
  }

  public OutputStream getOutputStream() throws IOException {
    return outputStream;
  }

}
