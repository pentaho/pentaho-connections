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
