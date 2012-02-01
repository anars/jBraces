package com.anars.jbrackets;

import java.util.Locale;
import java.util.logging.Logger;

public abstract class SpanFormatter
{
  protected Logger _logger = Logger.getLogger(getClass().getCanonicalName());

  public String format(String string)
  {
    return (format(string, Locale.getDefault()));
  }

  abstract public String format(String string, Locale locale);
}
