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

/**
 * Defines axis types - column or row.
 * 
 * @author wseyler
 * @see DataUtilities
 * @see CatagoryDatasetChartDefinition
 * @see PieDatasetChartDefinition
 * @see PentahoDataTransmuter
 * 
 */
public interface IPentahoDataTypes {
  public static final int AXIS_COLUMN = 0;

  public static final int AXIS_ROW = 1;

  // these type names are taken from w3c XML Schema data types

  public static final String TYPE_STRING = "string"; //$NON-NLS-1$

  public static final String TYPE_DOUBLE = "double"; //$NON-NLS-1$

  public static final String TYPE_FLOAT = "float"; //$NON-NLS-1$

  public static final String TYPE_INT = "integer"; //$NON-NLS-1$

  public static final String TYPE_DECIMAL = "decimal"; //$NON-NLS-1$

  public static final String TYPE_DATE = "dateTime"; //$NON-NLS-1$

  public static final String TYPE_LONG = "long"; //$NON-NLS-1$

  public static final String TYPE_BOOLEAN = "boolean"; //$NON-NLS-1$

  public static final String DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss"; //$NON-NLS-1$

}
