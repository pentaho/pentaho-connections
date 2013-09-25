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

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.pentaho.commons.connection.DataUtilities;

@RunWith( Parameterized.class )
public class NumberConverterTest {

  private NumberFormat numFmt = NumberFormat.getNumberInstance();

  private NumberFormat curFmt = NumberFormat.getCurrencyInstance();

  private double expected = 1699.01;

  private Object[] rowData;

  private boolean testPass;

  public NumberConverterTest( boolean testPass, double expected, Object[] rowData ) {
    this.testPass = testPass;
    this.expected = expected;
    this.rowData = rowData;
  }

  @Test
  public void toNumbersDouble() throws Exception {
    try {
      List<Number> num = DataUtilities.toNumbers( rowData, numFmt, curFmt );
      assertEquals( rowData.length, num.size() );
      for ( int i = 0; i < rowData.length; i++ ) {
        assertEquals( expected, num.get( i ).doubleValue(), 0.0 );
      }
    } catch ( IllegalArgumentException e ) {
      if ( testPass ) {
        throw ( e );
      }
    }
  }

  @SuppressWarnings( "rawtypes" )
  @Parameters
  public static Collection values() {
    return Arrays.asList( new Object[][] {
      { true, 1699.01, new Object[] { "1,699.01", "$1,699.01", "1699.01", "1699.01abc", new Double( 1699.01 ) } }, //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      { true, -1699.01, new Object[] { "-1699.01" } }, //$NON-NLS-1$
      { true, 1699.0, new Object[] { 1699 } }, { false, 1699.01, new Object[] { "abc1699.01" } }, //$NON-NLS-1$
      { false, 0.0, new Object[] { null } }, { false, 0.0, new Object[] { new ArrayList() } }, } );
  }
}
