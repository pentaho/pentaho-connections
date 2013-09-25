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
