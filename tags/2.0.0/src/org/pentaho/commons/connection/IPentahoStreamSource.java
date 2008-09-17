package org.pentaho.commons.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IPentahoStreamSource {

  public abstract String getContentType();

  public abstract InputStream getInputStream() throws IOException;

  public abstract String getName();

  public abstract OutputStream getOutputStream() throws IOException;
}
