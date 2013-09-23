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

package org.pentaho.commons.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

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
