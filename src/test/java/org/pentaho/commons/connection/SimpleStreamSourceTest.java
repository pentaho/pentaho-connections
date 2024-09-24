/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2028-08-13
 ******************************************************************************/
package org.pentaho.commons.connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

public class SimpleStreamSourceTest extends TestCase {

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
