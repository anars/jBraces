package com.anars.jbrackets.formatters;

import com.anars.jbrackets.SpanFormatter;

import java.text.NumberFormat;

import java.util.Locale;
import java.util.logging.Level;

public class FormatLongNumberSpanFormatter
  extends SpanFormatter
{
  public String format(String string, Locale locale)
  {
    string = string.replaceAll("[^0-9\\-]", "");
    long value = 0;
    try
      {
        value = Long.parseLong(string);
      }
    catch (Exception exception)
      {
        _logger.log(Level.SEVERE, "Unable to parse value", exception);
      }
    return (NumberFormat.getInstance(locale).format(value));
  }
}
