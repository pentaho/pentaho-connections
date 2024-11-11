/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/

package org.pentaho.commons.connection;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
  private static final String BUNDLE_NAME = "org.pentaho.commons.connection.messages"; //$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle( BUNDLE_NAME );

  private Messages() {
  }

  public static String getString( String key ) {
    // TODO Auto-generated method stub
    try {
      return RESOURCE_BUNDLE.getString( key );
    } catch ( MissingResourceException e ) {
      return '!' + key + '!';
    }
  }

  public static String getString( final String key, final String... params ) {
    try {
      return MessageFormat.format( RESOURCE_BUNDLE.getString( key ), (Object[]) params );
    } catch ( Exception e ) {
      return '!' + key + '!';
    }
  }

}
