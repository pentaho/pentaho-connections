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

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith( Parameterized.class )
public class DataUtilitiesNumberTest {

  private NumberFormat numFmt;

  private NumberFormat curFmt;

  private double expected = 1699.01;

  private Object[] rowData;

  private boolean testPass;

  public DataUtilitiesNumberTest( boolean testPass, double expected, Locale locale, Object[] rowData ) {
    this.testPass = testPass;
    this.expected = expected;
    this.rowData = rowData;
    numFmt = NumberFormat.getNumberInstance( locale );
    curFmt = NumberFormat.getCurrencyInstance( locale );
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
      { true, 1699.01, Locale.US, new Object[] { "1,699.01", "$1,699.01", "1699.01", "1699.01abc", new Double( 1699.01 ) } },
      { true, -1699.01, Locale.US, new Object[] { "-1699.01" } },
      { true, 1699.0, Locale.US, new Object[] { 1699 } }, { false, 1699.01, Locale.US, new Object[] { "abc1699.01" } },
      { false, 0.0, Locale.US, new Object[] { null } }, { false, 0.0, Locale.US, new Object[] { new ArrayList() } },
      { true, 1699.01, new Locale( "pt" ), new Object[] { "1.699,01", "1.699,01€", "1699,01", "1699,01abc", new Double( 1699.01 ) } },
      { true, -1699.01, new Locale( "pt" ), new Object[] { "-1699,01" } },
      { true, 1699.0, new Locale( "pt" ), new Object[] { 1699 } }, { false, 1699.01, new Locale( "pt" ), new Object[] { "abc1699,01" } },
      { false, 0.0, new Locale( "pt" ), new Object[] { null } }, { false, 0.0, new Locale( "pt" ), new Object[] { new ArrayList() } }, } );
  }
}
