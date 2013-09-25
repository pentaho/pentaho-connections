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

package org.pentaho.commons.connection.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.pentaho.commons.connection.SimpleStreamSource;

public class StreamSourceTest extends TestCase {

  public void testNameAndType() {

    SimpleStreamSource source = new SimpleStreamSource( "test name", "test type", null, null ); //$NON-NLS-1$ //$NON-NLS-2$

    assertTrue( source.getName().equals( "test name" ) ); //$NON-NLS-1$
    assertTrue( source.getContentType().equals( "test type" ) ); //$NON-NLS-1$

  }

  public void testInputStream() throws Exception {

    ByteArrayInputStream in1 = new ByteArrayInputStream( "test input data".getBytes() ); //$NON-NLS-1$
    SimpleStreamSource source = new SimpleStreamSource( "test name", "test type", in1, null ); //$NON-NLS-1$ //$NON-NLS-2$

    InputStream in2 = source.getInputStream();

    byte[] b = new byte[100];
    int n = in2.read( b );
    assertEquals( 15, n );
    String content = new String( b, 0, n );
    assertEquals( "test input data", content ); //$NON-NLS-1$

  }

  public void testOutputStream() throws Exception {

    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
    SimpleStreamSource source = new SimpleStreamSource( "test name", "test type", null, out1 ); //$NON-NLS-1$ //$NON-NLS-2$

    OutputStream out2 = source.getOutputStream();

    out2.write( "test output data".getBytes() ); //$NON-NLS-1$

    byte[] b = out1.toByteArray();

    String content = new String( b );
    assertEquals( "test output data", content ); //$NON-NLS-1$

  }

}
