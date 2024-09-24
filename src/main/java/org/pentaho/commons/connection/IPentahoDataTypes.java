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
