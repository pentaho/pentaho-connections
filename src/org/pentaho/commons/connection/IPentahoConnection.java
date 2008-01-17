/*
 * Copyright 2006 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the Mozilla Public License, Version 1.1, or any later version. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.mozilla.org/MPL/MPL-1.1.txt. The Original Code is the Pentaho 
 * BI Platform.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
 *
 * Created Aug 26, 2005 
 * @author wseyler
 */

package org.pentaho.commons.connection;

import java.util.List;
import java.util.Properties;

/**
 * A PentahoConnection represents metadata and functions required to execute
 * queries against data sources. Also contains constants that are properties
 * in data components <tt>SQLLookupRule</tt>, <tt>MDXLookupRule</tt>.
 * 
 * @author wseyler
 * 
 * @see SQLLookupRule
 * @see MDXLookupRule
 */
public interface IPentahoConnection {

  public static final int NONE_DATASOURCE = -1;

  public static final int SQL_DATASOURCE = 0;

  public static final int MDX_DATASOURCE = 1;

  public static final int XML_DATASOURCE = 2;

  public static final int HQL_DATASOURCE = 3;

  /**
   * Setting for class name used to look up a connection in the pentaho.xml.
   */
  public static final String CLASSNAME_KEY = "className"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * JNDI (Java Naming and Directory Interface) name for the database connection. 
   */
  public static final String JNDI_NAME_KEY = "jndiName"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * JDBC Driver
   */
  public static final String DRIVER_KEY = "driver"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * JDBC URL.
   */
  public static final String LOCATION_KEY = "location"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * user name to connect to the JDBC driver
   */
  public static final String USERNAME_KEY = "userName"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * password to connect to the JDBC driver
   */
  public static final String PASSWORD_KEY = "password"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the
   * database query (MDX, SQL, etc).
   */
  public static final String QUERY_KEY = "query"; //$NON-NLS-1$

  /**
   * Array of the XML element keys defined above.
   */
  public static final String[] KEYS = new String[] { CLASSNAME_KEY, JNDI_NAME_KEY, DRIVER_KEY, LOCATION_KEY,
      USERNAME_KEY, PASSWORD_KEY, QUERY_KEY };

  /**
   * closes the connection
   */
  void close();

  /**
   * @return the last query string executed
   */
  String getLastQuery();

  /**
   * @param query -
   *            SQL (like) query string. May be datasource specfic
   * @return result set of the query
   */
  IPentahoResultSet executeQuery(String query) throws Exception;

  /**
   * @param query -
   *            SQL (like) query string. May be datasource specfic
   * @param parameters - List of objects to bind into prepared query
   * @return result set of the query
   */
  IPentahoResultSet prepareAndExecuteQuery(String query, List parameters) throws Exception;

  /**
   * @return true if the connection supports prepared queries
   */
  boolean preparedQueriesSupported();

  /**
   * @return the last resultset from the last query executed
   */
  IPentahoResultSet getResultSet();

  /**
   * @return true if this connection has been closed
   */
  boolean isClosed();

  /**
   * @return true if this connection is read only
   * 
   * NOTE: Current implementation for all connections are read only
   */
  boolean isReadOnly();

  /**
   * Clears any warnings cached by the connection
   */
  void clearWarnings();

  /**
   * Connects to the data source using the supplied properties.
   * @param props Datasource connection properties
   * @return true if the connection was successful
   */
  boolean connect(Properties props);

  /**
   * The maximum rows that will be returned by the next query
   * @param maxRows
   */
  void setMaxRows(int maxRows);

  /**
   * Size of the fetch buffer used when retrieving rows from the
   * underlying database.
   * @param fetchSize
   */
  void setFetchSize(int fetchSize);

  /**
   * @return true if the connection has been properly initialized.
   */
  public boolean initialized();

  /**
   * returns the type of connection
   * @return
   */
  public int getDatasourceType();
}
