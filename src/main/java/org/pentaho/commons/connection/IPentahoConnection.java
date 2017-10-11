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

import java.util.List;
import java.util.Properties;

/**
 * A PentahoConnection represents metadata and functions required to execute queries against data sources. It also contains
 * constants that are properties in data components <tt>SQLLookupRule</tt> and <tt>MDXLookupRule</tt>.
 * 
 * @author wseyler
 * 
 * @see SQLLookupRule
 * @see MDXLookupRule
 */
public interface IPentahoConnection {

  public static final int NONE_DATASOURCE = -1;

  public static final String SQL_DATASOURCE = "SQL"; //$NON-NLS-1$

  public static final String MDX_DATASOURCE = "MDX"; //$NON-NLS-1$

  public static final String MDX_OLAP4J_DATASOURCE = "MDXOlap4j"; //$NON-NLS-1$

  public static final String XML_DATASOURCE = "XML"; //$NON-NLS-1$

  public static final String HQL_DATASOURCE = "HQL"; //$NON-NLS-1$

  /**
   * Setting for class name used to look up a connection in the pentaho.xml.
   */
  public static final String CLASSNAME_KEY = "className"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the JNDI (Java Naming and Directory Interface) name
   * for the database connection.
   */
  public static final String JNDI_NAME_KEY = "jndiName"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the JDBC driver.
   */
  public static final String DRIVER_KEY = "driver"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the JDBC URL.
   */
  public static final String LOCATION_KEY = "location"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the user name used to connect to the JDBC driver.
   */
  public static final String USERNAME_KEY = "userName"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the password used to connect to the JDBC driver.
   */
  public static final String PASSWORD_KEY = "password"; //$NON-NLS-1$

  /**
   * Defines the XML element in the component-definition that holds the database query (e.g. MDX, SQL).
   */
  public static final String QUERY_KEY = "query"; //$NON-NLS-1$

  /**
   * Standard key to use for a connection property.
   */
  public static final String CONNECTION = "connection"; //$NON-NLS-1$

  /**
   * Standard key to use for a connection's name property.
   */
  public static final String CONNECTION_NAME = "connection_name"; //$NON-NLS-1$

  /**
   * Standard key to use for a provider property.
   */
  public static final String PROVIDER = "provider"; //$NON-NLS-1$

  /**
   * Array of the XML element keys defined above.
   */
  public static final String[] KEYS = new String[] { CLASSNAME_KEY, JNDI_NAME_KEY, DRIVER_KEY, LOCATION_KEY,
    USERNAME_KEY, PASSWORD_KEY, QUERY_KEY };

  /**
   * Sets the properties to be used when the connection is made. The standard keys for the properties are defined in
   * this interface.
   * 
   * @param props Properties to be used when the connection is made.
   */
  void setProperties( Properties props );

  /**
   * Closes the connection.
   */
  void close();

  /**Retrieves the string representation of the last executed query.
   * @return Returns the last query string executed.
   */
  String getLastQuery();

  /**
   * Executes the specified query against the connection.
   * @param query
   *          SQL-like query string. May be data source specific.
   * @return Returns the result set of the query.
   */
  IPentahoResultSet executeQuery( String query ) throws Exception;

  /**Builds the query based on the pattern and parameters list and executes it against the connection.
   * @param query
   *          SQL-like query string. May be data source specific.
   * @param parameters
   *          List of objects to bind into prepared query.
   * @return Returns result set of the query.
   */
  @SuppressWarnings( "rawtypes" )
  IPentahoResultSet prepareAndExecuteQuery( String query, List parameters ) throws Exception;

  /**Checks if the given connection supports prepared queries. 
   * 
   * @return Returns true if the connection supports prepared queries.
   */
  boolean preparedQueriesSupported();

  /**Returns resultset of the last executed query.
   * @return Returns the resultset from the last query executed.
   */
  IPentahoResultSet getResultSet();

  /**Checks if the connection is closed or not.
   * @return Returns true if this connection has been closed.
   */
  boolean isClosed();

  /**Checks if the connection is read only or not.
   * @return true if this connection is read only.
   * 
   *         NOTE: Current implementation for all connections is read only.
   */
  boolean isReadOnly();

  /**
   * Clears any warnings cached by the connection.
   */
  void clearWarnings();

  /**
   * Connects to the data source using the supplied properties.
   * 
   * @param props
   *          Data source connection properties.
   * @return Returns true if the connection was successful.
   */
  boolean connect( Properties props );

  /**
   * Sets maximum rows that will be returned by the next query.
   * 
   * @param maxRows Maximum rows that are returned by the next query.
   */
  void setMaxRows( int maxRows );

  /**
   * Sets size of the fetch buffer used when retrieving rows from the underlying database.
   * 
   * @param fetchSize The size of the buffer.
   */
  void setFetchSize( int fetchSize );

  /**Checks if the connection is initialized or not.
   * @return Return true if the connection has been properly initialized.
   */
  public boolean initialized();

  /**
   * Returns the connection type.
   * 
   * @return Returns the type of the connection.
   */
  public String getDatasourceType();

}
