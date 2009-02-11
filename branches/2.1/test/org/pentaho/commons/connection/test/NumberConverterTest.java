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

@RunWith(Parameterized.class)
public class NumberConverterTest {

  private NumberFormat numFmt = NumberFormat.getNumberInstance();

  private NumberFormat curFmt = NumberFormat.getCurrencyInstance();

  private double expected=1699.01;

  private Object[] rowData;
  
  private boolean testPass;
  

  public NumberConverterTest(boolean testPass, double expected, Object[] rowData) {
    this.testPass = testPass;
    this.expected = expected;
    this.rowData = rowData;
  }

  @Test
  public void toNumbersDouble() throws Exception {
    try {
    List<Number> num = DataUtilities.toNumbers(rowData, numFmt, curFmt);
    assertEquals(rowData.length, num.size());
    for(int i=0; i<rowData.length; i++){
      assertEquals(expected, num.get(i).doubleValue(), 0.0);
    }
    }catch (IllegalArgumentException e) {
      if(testPass) {
        throw(e);
      }
    }
  }

  @Parameters
  public static Collection values() {
    return Arrays.asList(new Object[][] { 
        { true, 1699.01, new Object[] { "1,699.01", "$1,699.01", "1699.01", "1699.01abc", new Double(1699.01) } },  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        { true, -1699.01, new Object[] { "-1699.01" } }, //$NON-NLS-1$
        { true, 1699.0, new Object[] { 1699 } }, 
        { false, 1699.01, new Object[] { "abc1699.01" } }, //$NON-NLS-1$
        { false, 0.0, new Object[] { null } },
        { false, 0.0, new Object[] { new ArrayList() } },
    });
  }
}
