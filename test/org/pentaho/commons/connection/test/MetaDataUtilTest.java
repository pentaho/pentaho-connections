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

import junit.framework.TestCase;

import org.pentaho.commons.connection.Messages;
import org.pentaho.commons.connection.MetaDataUtil;

@SuppressWarnings( { "all" } )
public class MetaDataUtilTest extends TestCase {

  static final String[] colHeadersMay = { "May", "May", "May" };
  static final String[] colHeaders2009 = { "2009", "2009", "2009" };
  static final String[] colHeadersABV = { "Actual", "Budget", "Variance" };

  public void testSimpleCase1() {

    // instancing the class should not cause problems
    new MetaDataUtil();

    // no row headers, single set of column headers

    String[][] columnHeaders = { { "Country", "Actual", "Budget" } };

    String[][] rowHeaders = null;

    String[] rowHeaderNames = null;

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 3, columnNames.length );

    assertEquals( "wrong column name", "Country", columnNames[0] );
    assertEquals( "wrong column name", "Actual", columnNames[1] );
    assertEquals( "wrong column name", "Budget", columnNames[2] );

  }

  public void testDoubleColumnHeaders() {

    // no row headers, double column headers

    String[][] columnHeaders = { colHeadersABV, colHeaders2009 };

    String[][] rowHeaders = null;

    String[] rowHeaderNames = null;

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 3, columnNames.length );

    String sep = Messages.getString( "MetaDataUtil.USER_DEFAULT_SEPARATOR" );

    assertEquals( "wrong column name", "Actual" + sep + "2009", columnNames[0] );
    assertEquals( "wrong column name", "Budget" + sep + "2009", columnNames[1] );
    assertEquals( "wrong column name", "Variance" + sep + "2009", columnNames[2] );

  }

  public void testTripleColumnHeaders() {

    // no row headers, triple column headers

    String[][] columnHeaders = { colHeadersMay, colHeaders2009, colHeadersABV };

    String[][] rowHeaders = null;

    String[] rowHeaderNames = null;

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 3, columnNames.length );

    String sep = Messages.getString( "MetaDataUtil.USER_DEFAULT_SEPARATOR" );
    assertTrue( "seperator message not found", !sep.endsWith( "!" ) );

    assertEquals( "wrong column name", "May" + sep + "2009" + sep + "Actual", columnNames[0] );
    assertEquals( "wrong column name", "May" + sep + "2009" + sep + "Budget", columnNames[1] );
    assertEquals( "wrong column name", "May" + sep + "2009" + sep + "Variance", columnNames[2] );

  }

  public void testTripleColumnHeadersFormatted() {

    // no row headers, triple column headers

    String[][] columnHeaders = { colHeadersMay, colHeaders2009, colHeadersABV };

    String[][] rowHeaders = null;

    String[] rowHeaderNames = null;

    String columnNameFormatStr = "{2} {0}/{1}";

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 3, columnNames.length );

    assertEquals( "wrong column name", "Actual May/2009", columnNames[0] );
    assertEquals( "wrong column name", "Budget May/2009", columnNames[1] );
    assertEquals( "wrong column name", "Variance May/2009", columnNames[2] );

  }

  public void testSingleRowAndColumnHeaders() {

    // single row headers without names, single column headers

    String[][] columnHeaders = { { "Actual", "Budget", "Variance" } };

    String[][] rowHeaders = { { "2008" }, { "2009" } };

    String[] rowHeaderNames = null;

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 4, columnNames.length );

    assertEquals( "wrong column name", "Row header 1", columnNames[0] );
    assertEquals( "wrong column name", "Actual", columnNames[1] );
    assertEquals( "wrong column name", "Budget", columnNames[2] );
    assertEquals( "wrong column name", "Variance", columnNames[3] );

  }

  public void testDoubleRowAndSingleColumnHeaders() {

    // double row headers without names, single column headers

    String[][] columnHeaders = { colHeadersABV };

    String[][] rowHeaders = { { "2008", "May" }, { "2009", "May" } };

    String[] rowHeaderNames = null;

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 5, columnNames.length );

    assertEquals( "wrong column name", "Row header 1", columnNames[0] );
    assertEquals( "wrong column name", "Row header 2", columnNames[1] );
    assertEquals( "wrong column name", "Actual", columnNames[2] );
    assertEquals( "wrong column name", "Budget", columnNames[3] );
    assertEquals( "wrong column name", "Variance", columnNames[4] );

  }

  public void testSingleRowHeaderNamesAndSingleColumnHeaders() {

    // single row headers with names, single column headers, no row header names

    String[][] columnHeaders = { colHeadersABV };

    String[][] rowHeaders = { { "2008" }, { "2009" } };

    String[] rowHeaderNames = { "Year" };

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 4, columnNames.length );

    assertEquals( "wrong column name", "Year", columnNames[0] );
    assertEquals( "wrong column name", "Actual", columnNames[1] );
    assertEquals( "wrong column name", "Budget", columnNames[2] );
    assertEquals( "wrong column name", "Variance", columnNames[3] );

  }

  public void testDoubleRowHeaderNamesAndSingleColumnHeaders() {

    // double row headers with names, single column headers, no row header names

    String[][] columnHeaders = { colHeadersABV };

    String[][] rowHeaders = { { "2008", "May" }, { "2009", "May" } };

    String[] rowHeaderNames = { "Year", "Month" };

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 5, columnNames.length );

    assertEquals( "wrong column name", "Year", columnNames[0] );
    assertEquals( "wrong column name", "Month", columnNames[1] );
    assertEquals( "wrong column name", "Actual", columnNames[2] );
    assertEquals( "wrong column name", "Budget", columnNames[3] );
    assertEquals( "wrong column name", "Variance", columnNames[4] );

  }

  public void testBadRowHeaderNames() {

    // double row headers, wrong number of row header names

    String[][] columnHeaders = { colHeadersABV };

    String[][] rowHeaders = { { "2008", "May" }, { "2009", "May" } };

    String[] rowHeaderNames = { "Year" };

    String columnNameFormatStr = null;

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 5, columnNames.length );

    assertEquals( "wrong column name", "Row header 1", columnNames[0] );
    assertEquals( "wrong column name", "Row header 2", columnNames[1] );
    assertEquals( "wrong column name", "Actual", columnNames[2] );
    assertEquals( "wrong column name", "Budget", columnNames[3] );
    assertEquals( "wrong column name", "Variance", columnNames[4] );

  }

  public void testDoubleRowHeaderNamesAndTripeColumnHeadersFormatted() {

    // double row headers with names, single column headers, no row header names

    String[][] columnHeaders = { colHeadersMay, colHeaders2009, colHeadersABV };

    String[][] rowHeaders = { { "PL 1", "SKU1" }, { "PL 1", "SKU2" } };

    String[] rowHeaderNames = { "Product Line", "Product Code" };

    String columnNameFormatStr = "{2} {0}/{1}";

    String[] columnNames =
        MetaDataUtil.generateColumnNames( columnHeaders, rowHeaders, rowHeaderNames, columnNameFormatStr );

    assertNotNull( "column names are null", columnNames );

    assertEquals( "wrong number of column names", 5, columnNames.length );

    assertEquals( "wrong column name", "Product Line", columnNames[0] );
    assertEquals( "wrong column name", "Product Code", columnNames[1] );
    assertEquals( "wrong column name", "Actual May/2009", columnNames[2] );
    assertEquals( "wrong column name", "Budget May/2009", columnNames[3] );
    assertEquals( "wrong column name", "Variance May/2009", columnNames[4] );

  }

}
