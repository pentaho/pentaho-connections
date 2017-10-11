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
